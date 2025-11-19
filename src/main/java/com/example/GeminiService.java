package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GeminiService {

    private final Client client;
    private final ObjectMapper objectMapper;

    public GeminiService() {
        // 1. Initialize the Client
        // We use the builder so we can explicitly set the API key from your specific env var "GEMINI_API_KEY".
        // If you used the standard "GOOGLE_API_KEY", you could just do 'new Client()'.
        String apiKey = System.getenv("GEMINI_API_KEY");

        if (apiKey == null || apiKey.isEmpty()) {
            throw new RuntimeException("GEMINI_API_KEY environment variable is not set!");
        }

        this.client = Client.builder()
                .apiKey(apiKey)
                .build();

        this.objectMapper = new ObjectMapper();
    }

    public ExtractedInternshipDTO extractFromUrl(String url) {
        try {
            // 2. SCRAPE: Fetch text from the URL using Jsoup
            // We set a user-agent so websites don't block us immediately.
            String webPageText = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .timeout(10000) // 10 seconds timeout
                    .get()
                    .text();

            // Truncate text if it's too huge to save tokens (20k chars is usually enough for job info)
            if (webPageText.length() > 20000) {
                webPageText = webPageText.substring(0, 20000);
            }

            // 3. PROMPT: Tell Gemini exactly what to do
            String prompt = String.format("""
                I have copied the text from a job posting URL (%s).
                Please extract the following fields: Company Name, Job Title
                
                Return the result ONLY as a raw JSON object with this exact structure:
                {
                    "company": "...",
                    "title": "...",
                }
                
                Do not include markdown formatting (like ```json). Just the raw JSON string.
                
                Here is the website text:
                %s
                """, url, webPageText);

            // 4. CALL AI: Use the official SDK method
            GenerateContentResponse response = client.models.generateContent(
                    "gemini-2.0-flash", // Or "gemini-1.5-flash" if 2.0 isn't available to you yet
                    prompt,
                    null
            );

            // 5. CLEAN & PARSE: Handle the response
            String responseText = response.text();

            System.out.println(responseText);

            // Gemini loves to add ```json fencing, so we strip it out to be safe
            String cleanJson = responseText
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();

            // Convert JSON string to your DTO
            return objectMapper.readValue(cleanJson, ExtractedInternshipDTO.class);

        } catch (IOException e) {
            throw new RuntimeException("Failed to scrape or parse the URL: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Gemini API error: " + e.getMessage(), e);
        }
    }
}
package com.example;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class InternshipService {




    private final InternshipRepository internshipRepository;
    private final UsersRepository usersRepository;

    public InternshipService(InternshipRepository internshipRepository, UsersRepository usersRepository) {
        this.internshipRepository = internshipRepository;
        this.usersRepository = usersRepository;
    }

    public InternshipResponseDTO createInternship(Users user, CreateOrUpdateInternshipDTO dto)
    {



        Internship internship = new Internship();
        internship.setCompany(dto.getCompany());
        internship.setTitle(dto.getTitle());
        internship.setStatus(dto.getStatus());
        internship.setUser(user);

        Internship saved = internshipRepository.save(internship);


        return new InternshipResponseDTO(
                saved.getId(),
                saved.getCompany(),
                saved.getTitle(),
                saved.getStatus(),
                saved.getCreatedAt()
        );



    }

    public List<InternshipResponseDTO> getInternships(Users user) {

        List<Internship> internships = internshipRepository.findAllByUserIdOrderByCreatedAtDesc(user.getId());

        List<InternshipResponseDTO> result = new ArrayList<>();

        for (Internship i : internships) {
            InternshipResponseDTO dto = new InternshipResponseDTO(
                    i.getId(),
                    i.getCompany(),
                    i.getTitle(),
                    i.getStatus(),
                    i.getCreatedAt()
            );

            result.add(dto);
        }

        return result;
    }

    public InternshipResponseDTO updateInternship(
            Users user,
            UUID internshipId,
            CreateOrUpdateInternshipDTO dto
    ){

        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new RuntimeException("Internship not found"));

        if (!internship.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Forbidden: You do not own this internship");
        }

        internship.setTitle(dto.getTitle());
        internship.setCompany(dto.getCompany());
        internship.setStatus(dto.getStatus());

        Internship saved = internshipRepository.save(internship);

        return new InternshipResponseDTO(
                saved.getId(),
                saved.getCompany(),
                saved.getTitle(),
                saved.getStatus(),
                saved.getCreatedAt()
        );

    }
    public void deleteInternship(UUID id, Users user) {

        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Internship not found"));

        if (!internship.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        internshipRepository.delete(internship);
    }




}

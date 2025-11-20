# OAuth2 Authentication Fixes & Improvements

## Issues Identified and Fixed

### 1. **OAuth2 Callback Redirect Issue** ✅ FIXED
**Problem:** After successful GitHub authentication, Spring Security was redirecting to `/` (backend root) instead of the frontend.

**Solution:**
- Added custom `successHandler` in `SecurityConfig` to redirect to `http://localhost:5173/internships?login=success`
- Added `failureHandler` to redirect to login page on authentication failure
- Updated frontend `App.jsx` to detect the `login=success` query parameter and refresh auth state

**Files Changed:**
- `src/main/java/com/example/SecurityConfig.java` - Added success/failure handlers
- `frontend/src/App.jsx` - Added callback detection
- `frontend/src/context/AuthContext.jsx` - Updated logout endpoint

### 2. **Logout Endpoint Missing** ✅ FIXED
**Problem:** No proper logout endpoint configured. Frontend was calling `/logout` but backend didn't have it configured.

**Solution:**
- Added logout configuration in `SecurityConfig` with endpoint `/api/logout`
- Configured to clear JSESSIONID cookie and invalidate session
- Returns JSON response on success

**Files Changed:**
- `src/main/java/com/example/SecurityConfig.java` - Added logout configuration
- `frontend/src/context/AuthContext.jsx` - Updated to use `/api/logout`

### 3. **Session and Cookie Configuration** ✅ FIXED
**Problem:** No explicit session/cookie configuration for cross-origin requests.

**Solution:**
- Added session configuration in `application.properties`:
  - Cookie name: `JSESSIONID`
  - HttpOnly: `true`
  - Secure: `false` (for local development)
  - SameSite: `lax`
  - Session timeout: `30m`
- Updated CORS configuration to expose `Set-Cookie` header
- Set session creation policy to `IF_REQUIRED`

**Files Changed:**
- `src/main/resources/application.properties` - Added session configuration
- `src/main/java/com/example/SecurityConfig.java` - Updated CORS and session management

### 4. **CustomOAuth2UserService Not Registered** ✅ FIXED
**Problem:** `CustomOAuth2UserService` was not being used by Spring Security.

**Solution:**
- Injected `OAuth2UserService` in `SecurityConfig` constructor
- Registered it in the `oauth2Login` configuration using `userInfoEndpoint().userService()`

**Files Changed:**
- `src/main/java/com/example/SecurityConfig.java` - Registered CustomOAuth2UserService

### 5. **OAuth2 Provider Configuration** ✅ ADDED
**Solution:**
- Added explicit OAuth2 provider configuration in `application.properties`:
  - Authorization URI
  - Token URI
  - User Info URI
  - Redirect URI
  - User name attribute

**Files Changed:**
- `src/main/resources/application.properties` - Added OAuth2 provider config

## OAuth2 Flow Diagram

```
┌─────────────┐
│   Frontend  │
│ (localhost: │
│   5173)     │
└──────┬──────┘
       │
       │ 1. User clicks "Sign In with GitHub"
       │    GET /oauth2/authorization/github
       ▼
┌─────────────────────────────────────┐
│         Backend (Spring Security)   │
│         (localhost:8080)            │
└──────┬──────────────────────────────┘
       │
       │ 2. Redirects to GitHub
       │    https://github.com/login/oauth/authorize
       ▼
┌─────────────┐
│   GitHub    │
│   OAuth     │
└──────┬──────┘
       │
       │ 3. User authorizes app
       │    Callback: /login/oauth2/code/github
       ▼
┌─────────────────────────────────────┐
│         Backend (Spring Security)   │
│  - Exchanges code for token         │
│  - Calls CustomOAuth2UserService    │
│  - Creates/updates user in DB       │
│  - Creates session                  │
└──────┬──────────────────────────────┘
       │
       │ 4. Success handler redirects
       │    http://localhost:5173/internships?login=success
       ▼
┌─────────────┐
│   Frontend  │
│  - Detects  │
│    ?login=  │
│  success    │
│  - Calls    │
│    /api/user│
│  - Updates  │
│    auth     │
│    state    │
└─────────────┘
```

## Logout Flow

```
┌─────────────┐
│   Frontend  │
│  User clicks│
│  "Sign Out" │
└──────┬──────┘
       │
       │ POST /api/logout
       │ (withCredentials: true)
       ▼
┌─────────────────────────────────────┐
│         Backend                     │
│  - Invalidates session              │
│  - Deletes JSESSIONID cookie        │
│  - Clears authentication            │
│  - Returns 200 OK                   │
└──────┬──────────────────────────────┘
       │
       │ Redirects to /home
       ▼
┌─────────────┐
│   Frontend  │
│  - Clears   │
│    user     │
│    state    │
│  - Shows    │
│    home     │
│    page     │
└─────────────┘
```

## GitHub OAuth App Configuration

Make sure your GitHub OAuth App has these settings:

1. **Authorization callback URL:**
   ```
   http://localhost:8080/login/oauth2/code/github
   ```

2. **Homepage URL:**
   ```
   http://localhost:5173
   ```

3. **Application name:**
   ```
   Internship Tracker
   ```

## Testing the Flow

1. **Login:**
   - Navigate to `http://localhost:5173/login`
   - Click "Continue with GitHub"
   - Authorize the app on GitHub
   - Should redirect to `/internships?login=success`
   - User should be authenticated

2. **Logout:**
   - Click "Sign Out" in navbar
   - Should redirect to `/home`
   - User should be logged out
   - Session cookie should be cleared

3. **Session Persistence:**
   - Login and close browser
   - Reopen and navigate to `/internships`
   - Should still be authenticated (if session hasn't expired)

## Common Issues & Solutions

### Issue: "Redirect URI mismatch"
**Solution:** Ensure GitHub OAuth App callback URL matches exactly:
```
http://localhost:8080/login/oauth2/code/github
```

### Issue: Cookies not being sent
**Solution:** 
- Ensure `withCredentials: true` in all axios requests
- Check CORS configuration allows credentials
- Verify cookie domain/path settings

### Issue: Session not persisting
**Solution:**
- Check session timeout in `application.properties`
- Verify session cookie settings
- Ensure browser allows cookies for localhost

### Issue: CORS errors
**Solution:**
- Verify frontend origin in CORS config: `http://localhost:5173`
- Check `allowCredentials: true` in CORS config
- Ensure `withCredentials: true` in frontend requests

## Files Modified

### Backend:
1. `src/main/java/com/example/SecurityConfig.java` - Complete rewrite with proper OAuth2 and logout config
2. `src/main/resources/application.properties` - Added session and OAuth2 provider config

### Frontend:
1. `frontend/src/App.jsx` - Added OAuth callback detection
2. `frontend/src/context/AuthContext.jsx` - Updated logout endpoint
3. `frontend/vite.config.js` - Added logout proxy

## Next Steps

1. **Update GitHub OAuth App settings** with the correct callback URL
2. **Test the complete flow** (login → use app → logout)
3. **For production**, update:
   - Cookie `secure` flag to `true`
   - CORS origins to production domain
   - Session timeout as needed
   - OAuth redirect URIs to production URLs


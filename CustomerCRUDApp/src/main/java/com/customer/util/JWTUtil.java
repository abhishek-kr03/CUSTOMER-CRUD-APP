package com.customer.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JWTUtil {
    // Secret key used for signing the JWTs. It should be kept secure and private.
    private static final String SECRET_KEY = "your_super_long_secret_key_which_is_at_least_32_bytes_long";

    /**
     * Generates a JWT token with the specified login ID.
     *
     * @param loginId The login ID to include in the JWT.
     * @return The generated JWT as a String, or null if an error occurs.
     */
    public static String generateToken(String loginId) {
        try {
            // Create a JWT algorithm instance with the secret key
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            
            // Create and sign the JWT with the provided login ID
            return JWT.create()
                    .withSubject(loginId)  // Set the subject (user identifier)
                    .sign(algorithm);  // Sign the JWT with the algorithm
        } catch (Exception exception) {
            // Print the stack trace and return null if an exception occurs
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Validates the provided JWT token.
     *
     * @param token The JWT token to validate.
     * @return true if the token is valid, false otherwise.
     */
    public static boolean validateToken(String token) {
        try {
            // Create a JWT algorithm instance with the secret key
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            
            // Create a JWT verifier instance with the algorithm
            JWTVerifier verifier = JWT.require(algorithm).build();
            
            // Verify the token
            verifier.verify(token);
            
            // Return true if the token is valid
            return true;
        } catch (Exception e) {
            // Print the stack trace and return false if an exception occurs
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Extracts the login ID from the provided JWT token.
     *
     * @param token The JWT token to extract the login ID from.
     * @return The login ID from the token, or null if an exception occurs.
     */
    public static String getLoginIdFromToken(String token) {
        try {
            // Create a JWT algorithm instance with the secret key
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            
            // Create a JWT verifier instance with the algorithm
            JWTVerifier verifier = JWT.require(algorithm).build();
            
            // Verify the token and decode it
            DecodedJWT jwt = verifier.verify(token);
            
            // Return the subject (login ID) from the decoded token
            return jwt.getSubject();
        } catch (Exception e) {
            // Print the stack trace and return null if an exception occurs
            e.printStackTrace();
            return null;
        }
    }
}

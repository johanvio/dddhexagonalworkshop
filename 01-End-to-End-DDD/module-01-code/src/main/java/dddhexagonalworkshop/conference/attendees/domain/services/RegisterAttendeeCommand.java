package dddhexagonalworkshop.conference.attendees.domain.services;

/**
 * "Commands (also known as modifiers) are operations that aﬀect some change to the systems (for a simple example, by setting a variable)."
 * Eric Evans, Domain-Driven Design: Tackling Complexity in the Heart of Software, 2003.
 *
 * Command to register an attendee.
 * This command is used to initiate the registration process for an attendee.
 */
public record RegisterAttendeeCommand(String email) {
    
    /**
     * Compact constructor for validation.
     * This runs automatically when the record is created.
     */
    public RegisterAttendeeCommand {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        
        // Basic email format validation
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must contain @ symbol");
        }
    }
}
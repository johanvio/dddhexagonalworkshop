package dddhexagonalworkshop.conference.attendees.domain.aggregates;

import dddhexagonalworkshop.conference.attendees.domain.services.AttendeeRegistrationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AttendeeTest {
    private final String email = "smeagol@riverfolk.net";

    @Test
    public void testRegisterAttendee_ValidEmail() {
        AttendeeRegistrationResult result = Attendee.registerAttendee(email);

        assertNotNull(result, "Registration result should not be null");
        assertNotNull(result.attendee(), "Attendee should not be null");
        assertNotNull(result.attendeeRegisteredEvent(), "Event should not be null");
        assertEquals(email, result.attendee().getEmail(), "Attendee email should match");
        assertEquals(email, result.attendeeRegisteredEvent().email(), "Event email should match");
    }
}

package dddhexagonalworkshop.conference.attendees.persistence;

import jakarta.persistence.*;

/**
 * "An Entity models an individual thing. Each Entity has a unique identity in that you can
 * distinguish its individuality from among all other Entities of the same or a different type."
 * Vaugn Vernon, Domain-Driven Design Distilled, 2016
 */
@Entity
@Table(name = "attendee")
public class AttendeeEntity {

    /**
     * Database primary key - technical identity for persistence.
     * This is different from business identity (email) in the domain model.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Business data mapped to database column.
     * Keep column names simple and clear.
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * Default no-argument constructor required by Hibernate/JPA.
     * Protected visibility prevents direct instantiation while
     * allowing framework access.
     */
    protected AttendeeEntity() {
        // Required by JPA specification
    }

    /**
     * Constructor for creating new entity instances.
     * Package-private to control creation within persistence layer.
     *
     * @param email The attendee's email address
     */
    protected AttendeeEntity(String email) {
        this.email = email;
    }

    /**
     * Getter for database ID.
     * Protected because external code shouldn't depend on database IDs.
     */
    protected Long getId() {
        return id;
    }

    /**
     * Getter for email.
     * Protected to keep access controlled within persistence layer.
     */
    protected String getEmail() {
        return email;
    }

    /**
     * Setter for email.
     * Protected to keep access controlled within persistence layer.
     */
    protected void setEmail(String email) {
        this.email = email;
    }

    /**
     * String representation for debugging.
     */
    @Override
    public String toString() {
        return "AttendeeEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}

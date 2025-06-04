package dddhexagonalworkshop.conference.attendees.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * "A REPOSITORY represents all objects of a certain type as a conceptual set (usually emulated). It acts like a collection, except with more elaborate querying capability. Objects of the appropriate type are added and removed, and the machinery behind the REPOSITORY inserts them or deletes them from the database."
 * Eric Evans, Domain-Driven Design: Tackling Complexity in the Heart of Software, 2003.
 *
 * We are using PanacheRepository from Quarkus, which provides a set of methods to interact with the database.
 */
@ApplicationScoped
public class AttendeeRepository implements PanacheRepository<AttendeeEntity> {

  /**
    * Persists an Attendee aggregate to the database.
    * Converts the domain aggregate to a persistence entity and saves it.
    *
    * @param aggregate The Attendee domain aggregate to persist
      */
      public void persist(Attendee aggregate) {
      // Convert domain aggregate to persistence entity
      AttendeeEntity attendeeEntity = fromAggregate(aggregate);

      // Use inherited Panache method to persist
      persist(attendeeEntity);
      }

  /**
    * Finds an attendee by their email address.
    * Returns a domain aggregate, not a persistence entity.
    *
    * @param email The email address to search for
    * @return Optional containing the Attendee aggregate if found
      */
      public Optional<Attendee> findByEmail(String email) {
      // Query using persistence entity
      Optional<AttendeeEntity> entityOpt = find("email", email).firstResultOptional();

      // Convert persistence entity back to domain aggregate
      return entityOpt.map(this::toAggregate);
      }

  /**
    * Removes an attendee from the database.
    *
    * @param attendee The Attendee aggregate to remove
      */
      public void remove(Attendee attendee) {
      // Find the corresponding entity and delete it
      find("email", attendee.getEmail())
      .firstResultOptional()
      .ifPresent(this::delete);
      }

  /**
    * Converts a domain Attendee aggregate to an AttendeeEntity for persistence.
    * This is where domain concepts are mapped to database structures.
    *
    * @param attendee The domain aggregate
    * @return The persistence entity
      */
      private AttendeeEntity fromAggregate(Attendee attendee) {
      return new AttendeeEntity(attendee.getEmail());
      }

  /**
    * Converts an AttendeeEntity from the database to a domain Attendee aggregate.
    * This reconstitutes the domain object from persisted data.
    *
    * @param entity The persistence entity
    * @return The domain aggregate
      */
      private Attendee toAggregate(AttendeeEntity entity) {
      // Note: In a real system, you might need a factory method on Attendee
      // to reconstruct from persisted state, since registerAttendee() is for new attendees
      return Attendee.fromPersistedData(entity.getEmail());
      }
      }
}

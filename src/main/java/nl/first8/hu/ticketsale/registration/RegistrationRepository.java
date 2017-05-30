package nl.first8.hu.ticketsale.registration;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationRepository {

    private final EntityManager entityManager;

    @Autowired
    public RegistrationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Persists the given <code>account</code> in the underlying data source.
     *
     * @param account the account to persist
     *
     * @throws EntityExistsException if the entity already exists
     */
    public void insert(final Account account) {
        entityManager.persist(account);
    }

    /**
     * Updates the account with the <code>id</code> that matches the
     * <code>id</code> of the given <code>account</code>
     *
     * @param account the updates field(s) of the account
     * @return a <i>new</i> Account instances, containing the latest view of
     * this Account entity.
     *
     * @throws IllegalArgumentException if the given <code>account</code> is not
     * already an attached entity
     */
    public Account update(Account account) {
        return entityManager.merge(account);
    }

    /**
     * Optionally returns the Account identified by the given <code>id</code>
     *
     * @param id the id of the Account to find
     * @return Returns the Account identified by the given <code>id</code> or an
     * {@link Optional#empty() empty} Optional if no Account could be identified
     * with the given <code>id</code>.
     */
    public Optional<Account> findById(final Long id) {
        return Optional.ofNullable(entityManager.find(Account.class, id));
    }

    /**
     * Optionally returns the Account identified by the given <code>id</code>
     *
     * @param emailAddress the email address of the Account to find
     * @return Returns the Account identified by the given
     * <code>emailAddress</code> or an {@link Optional#empty() empty} Optional
     * if no Account could be identified with the given
     * <code>emailAddress</code>.
     */
    Optional<Account> findByEmailAddress(String emailAddress) {
        try {
            return Optional.of(entityManager.createQuery("SELECT a FROM Account a WHERE a.emailAddress =:emailAddress", Account.class)
                    .setParameter("emailAddress", emailAddress)
                    .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    /**
     * Returns a list of all {@link Account}s in the underlying data source
     *
     * @return a list of all known {@link Account}s or an empty list if no
     * {@link Account}s exist in the underlying data source
     */
    public List<Account> findAll() {
        return entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
    }

}

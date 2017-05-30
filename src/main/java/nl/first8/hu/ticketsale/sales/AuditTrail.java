package nl.first8.hu.ticketsale.sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.first8.hu.ticketsale.registration.Account;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Rogier on 30-5-2017.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "audit_trail")
public class AuditTrail implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Sale sale;

    @OneToOne
    private Account account;

}

package dev.chernykh.studentmanagement.students;

import dev.chernykh.studentmanagement.groups.Group;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "f_name")
    private String firstName;
    @Column(name = "l_name")
    private String lastName;
    @Column(name = "m_name")
    private String middleName;
    @Column(name = "dob")
    private LocalDate dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}

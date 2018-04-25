package dev.chernykh.studentmanagement.students;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class StudentDto {
    private long id;
    @NotBlank(message = "NotBlank.studentDto.firstName")
    private String firstName;
    @NotBlank(message = "NotBlank.studentDto.lastName")
    private String lastName;
    private String middleName;
    @NotNull(message = "NotNull.studentDto.dateOfBirth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @NotBlank(message = "NotBlank.studentDto.group")
    private String groupName;

    public StudentDto(Student student) {
        this.id = student.getId();
        this.lastName = student.getLastName();
        this.firstName = student.getFirstName();
        this.middleName = student.getMiddleName();
        this.dateOfBirth = student.getDateOfBirth();
        this.groupName = student.getGroup().getName();
    }

    public Student toStudent() {
        Student student = new Student();
        student.setLastName(lastName);
        student.setFirstName(firstName);
        student.setMiddleName(middleName);
        student.setDateOfBirth(dateOfBirth);
        return student;
    }
}

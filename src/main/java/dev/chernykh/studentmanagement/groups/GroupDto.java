package dev.chernykh.studentmanagement.groups;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class GroupDto {
    private long id;
    @Pattern(regexp = "[A-Za-z]{1,5}-\\d{2}", message = "Pattern.groupDto.name")
    @NotBlank(message = "NotBlank.groupDto.name")
    private String name;
    @NotBlank(message = "NotBlank.groupDto.facultyName")
    private String facultyName;

    GroupDto(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.facultyName = group.getFacultyName();
    }

    public Group toGroup() {
        Group group = new Group();
        group.setId(id);
        group.setName(name);
        group.setFacultyName(facultyName);
        return group;
    }
}

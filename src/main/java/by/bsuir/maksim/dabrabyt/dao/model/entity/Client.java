package by.bsuir.maksim.dabrabyt.dao.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    private Integer id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate birthdate;
    private Sex sex;
    private String phoneHome;
    private String phoneCell;
    private String email;
    private City actCity;
    private String actAddress;
    private Integer salary;
    private String passportSeries;
    private Integer passportNumber;
    private String passportIssuedBy;
    private LocalDate passportIssuedDate;
    private String passportIdentityNumber;
    private City passportCity;
    private Nationality nationality;
    private String passportBirthplace;
    private FamilyStatus familyStatus;
    private Disability disability;
    private Boolean retiree;

}

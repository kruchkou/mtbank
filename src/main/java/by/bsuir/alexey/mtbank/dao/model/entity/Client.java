package by.bsuir.alexey.mtbank.dao.model.entity;

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
    private String phoneHome;
    private String phoneCell;
    private String email;
    private String workPlace;
    private String workPosition;
    private City city;
    private String actAddress;
    private Integer salary;
    private String passportSeries;
    private Integer passportNumber;
    private String passportIssuedBy;
    private LocalDate passportIssuedDate;
    private String passportIdentityNumber;
    private String passportBirthplace;
    private String passportAddress;
    private Nationality nationality;
    private FamilyStatus familyStatus;
    private Disability disability;
    private Boolean retiree;

}

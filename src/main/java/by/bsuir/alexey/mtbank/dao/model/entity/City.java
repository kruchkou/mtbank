package by.bsuir.alexey.mtbank.dao.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

    private Integer id;
    private String name;

}

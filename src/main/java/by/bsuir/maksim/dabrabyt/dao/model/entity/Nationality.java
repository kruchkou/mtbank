package by.bsuir.maksim.dabrabyt.dao.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nationality {

    private Integer id;
    private String name;

}
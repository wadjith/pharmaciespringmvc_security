package cm.objis.wtt.pharmacie.domaine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private String email;
    private String name;
    private String lastName;
    private String roles;

}

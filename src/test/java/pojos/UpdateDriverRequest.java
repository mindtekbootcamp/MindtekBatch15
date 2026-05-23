package pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDriverRequest {

    private String uuid;
    private Integer id;
    private String full_name;
    private Boolean is_staff;
    private String status;
    private Boolean is_local;
    private Boolean twic;
    private String driving_license_exp;
    private String medical_certification_exp;

}

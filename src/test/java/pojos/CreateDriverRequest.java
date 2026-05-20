package pojos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateDriverRequest {
    private String full_name;
    private  String logbook_Email;
    private  String logbook_password;
    private  Boolean is_staff;
    private  Boolean is_local;
    private  Boolean twic;
    private String driving_license_exp;
    private  String medical_certification_exp;
    private List<String> contacts_phone;
    private List<String> contacts_viber;
    private List<String> contacts_other;


}

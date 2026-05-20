package pojos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateDriverResponse {
    private String uuid;
    private Integer id;
    private String full_driver_num;
    private List<String> schedule;
    private String full_name;
    private Boolean is_staff;
    private String status;
    private List<String> alerts;
    private String created_at;
    private String updated_at;
    private String driver_local_identifier;
    private List<String> contacts_phone;
    private List<String> contacts_email;
    private List<String> contacts_viber;
    private List<String> contacts_other;
    private String logbook_email;
    private String logbook_password;
    private Boolean is_local;
    private Boolean twic;
    private String driving_license_exp;
    private String medical_certification_exp;
    private String driver_number;

}

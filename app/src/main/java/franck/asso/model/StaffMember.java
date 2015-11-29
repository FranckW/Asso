package franck.asso.model;

import java.util.Date;

/**
 * Created by franc on 15/10/2015.
 */
public class StaffMember extends Member {
    protected Role role;

    public StaffMember(boolean gender, String firstName, String lastName, String birthDate, String email, String address, String phoneNumber, Role role) {
        super(gender, firstName, lastName, birthDate, email, address, phoneNumber);
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
}

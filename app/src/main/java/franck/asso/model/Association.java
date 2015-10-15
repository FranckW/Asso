package franck.asso.model;

import java.util.List;

/**
 * Created by franc on 15/10/2015.
 */
public class Association {
    protected String name;
    protected String address;
    protected String description;
    protected List<StaffMember> staff;
    protected List<Member> members;

    public Association(String name, String address, String description, List<StaffMember> staff, List<Member> members) throws Exception {
        if (this.staffIsOk(staff)) {
            this.name = name;
            this.address = address;
            this.description = description;
            this.staff = staff;
            this.members = members;
        } else {
            throw new Exception("An association needs at least a president and a treasurer.");
        }
    }

    private boolean staffIsOk(List<StaffMember> staff) {
        boolean hasPresident = false;
        boolean hasTreasurer = false;
        for (StaffMember staffMember : staff) {
            if (staffMember.getRole().toString().equals(Role.PRESIDENT.toString())) {
                hasPresident = true;
            }
            if (staffMember.getRole().toString().equals(Role.TREASURER.toString())) {
                hasTreasurer = true;
            }
        }
        return hasPresident && hasTreasurer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<StaffMember> getStaff() {
        return staff;
    }

    public void setStaff(List<StaffMember> staff) {
        this.staff = staff;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}

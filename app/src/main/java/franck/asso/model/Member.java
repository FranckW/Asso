package franck.asso.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by franc on 15/10/2015.
 */
public class Member implements Parcelable {
    protected boolean gender;
    protected String firstName;
    protected String lastName;
    protected String birthDate;
    protected String email;
    protected String address;
    protected String phoneNumber;

    public Member(boolean gender, String firstName, String lastName, String birthDate, String email, String address, String phoneNumber) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        String genderPrefix;
        if (gender) {
            genderPrefix = "Mr.";
        } else {
            genderPrefix = "Mrs.";
        }
        dest.writeString(genderPrefix);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(birthDate);
        dest.writeString(email);
        dest.writeString(address);
        dest.writeString(phoneNumber);
    }

    public static final Parcelable.Creator<Member> CREATOR = new Parcelable.Creator<Member>() {
        @Override
        public Member createFromParcel(Parcel source) {
            return new Member(source);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };

    public Member(Parcel in) {
        boolean genderBool;
        String genderString = in.readString();
        if (genderString.equals("Mr.")) {
            genderBool = true;
        } else genderBool = false;
        this.gender = genderBool;
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.birthDate = in.readString();
        this.email = in.readString();
        this.address = in.readString();
        this.phoneNumber = in.readString();
    }
}

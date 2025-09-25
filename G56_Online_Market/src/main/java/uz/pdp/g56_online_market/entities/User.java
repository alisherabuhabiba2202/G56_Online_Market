package uz.pdp.g56_online_market.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usename;
    private String password; // PBKDF2 hash
    private String profilePicture;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getUsename() {return usename;}
    public void setUsename(String usename) {this.usename = usename;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getProfilePicture() {return profilePicture;}
    public void setProfilePicture(String profilePicture) {this.profilePicture = profilePicture;}



}

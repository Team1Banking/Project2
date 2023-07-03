package com.revature.Project2.models;

import javax.persistence.*;

@Entity
@Table(name="Roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_id;

    @Column(unique = true)
    private String roleTitle;

    public Roles(){

    }

    public Roles(int role_id, String roleTitle) {
        this.role_id = role_id;
        this.roleTitle = roleTitle;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "role_id=" + role_id +
                ", roleTitle=" + roleTitle +
                '}';
    }
}

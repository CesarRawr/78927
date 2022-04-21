package com.access.control.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
  	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int habitacion;
    private String token;

    public User() { }

    public User(String nombre, int habitacion, String token) {
    	this.setNombre(nombre);
    	this.setHabitacion(habitacion);
    	this.setToken(token);
    }

    public int getId() {
    	return this.id;
    }

    public String getNombre() {
    	return this.nombre;
    }

    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }

    public int getHabitacion() {
    	return this.habitacion;
    }

    public void setHabitacion(int habitacion) {
    	this.habitacion = habitacion;
    }

    public String getToken() {
    	return this.token;
    }

    public void setToken(String token) {
    	this.token = token;
    }
}

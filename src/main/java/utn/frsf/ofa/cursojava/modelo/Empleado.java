/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

/**
 *
 * @author federicoaugustotschopp
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType=DiscriminatorType.INTEGER,name="TIPO_EMPLEADO")
public abstract class Empleado implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Integer id;
    protected String nombre;
    protected String correoElectronico;
    protected String cuil;
    protected Date fechaDeIngreso;
    protected Integer horasTrabajadas;
    @ManyToMany (mappedBy="empleados")
    protected List<Proyecto> proyectosAsignados;
    
    public abstract double salario();
    public void setNombre(String nom){
        this.nombre=nom;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getCorreo(){
        return this.correoElectronico;
        
    }
    
    public void setCorreo(String c){
        this.correoElectronico=c;
    }
    
    public String getCuil(){
        return this.cuil;
    }
    
    public void setCuil(String s){
        this.cuil=s;
    }
    public Date getFechaDeIngreso(){
        return this.fechaDeIngreso;
    }
    
    public void setFechaDeIngreso(Date d){
        this.fechaDeIngreso=d;
    }
    public Integer getHorasTrabajadas(){
        return this.horasTrabajadas;
    }
    
    public void setHorasTrabajadas(Integer i){
        this.horasTrabajadas=i;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Proyecto> getProyectosAsignados() {
        return proyectosAsignados;
    }

    public void setProyectosAsignados(List<Proyecto> proyectosAsignados) {
        this.proyectosAsignados = proyectosAsignados;
    }
    
    public abstract boolean esEfectivo();
    public abstract boolean esContratado();
}

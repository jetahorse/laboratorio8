/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.modelo;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import utn.frsf.ofa.cursojava.dao.ConexionJPA;

/**
 *
 * @author federicoaugustotschopp
 */
public class ProyectoEmpleadoTest {
    
    private EntityManager em;
    
    public ProyectoEmpleadoTest() {
    }
    
    @Before
    public void setUp() {
        this.em=ConexionJPA.get();
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void CrearProyectoEmpleado(){
        em.getTransaction().begin();
        Proyecto p1=new Proyecto();
        p1.setDescripcion("Detalles de tareas - Proyecto1");
        p1.setNombre("Proyecto X1");
        p1.setPresupuestoMaximo(1000.00);
        Proyecto p2=new Proyecto();
        p1.setDescripcion("Detalle 3");
        p1.setNombre("Proyecto X2");
        p1.setPresupuestoMaximo(5000.00);
        Empleado emp1 = new Contratado(); 
        emp1.setCuil("999"); 
        emp1.setNombre("Empleado01"); 
        Empleado emp2 = new Efectivo(); 
        emp2.setCuil("555"); 
        emp2.setNombre("Empleado02");
        
        em.persist(emp1);
        em.persist(emp2);
        p1.setEmpleados(new ArrayList<>());
        p1.getEmpleados().add(emp1);
        p1.getEmpleados().add(emp2);
        p2.setEmpleados(new ArrayList<>());
        p2.getEmpleados().add(emp2);
        em.persist(p1);
        em.persist(p2);
        System.out.println("ANTES DE FLUSH DE TX ");
        em.flush();
        em.getTransaction().commit();
        
        EntityManager em2 = ConexionJPA.get();
        Proyecto resultPry1 = (Proyecto) em2.createQuery("SELECT p FROM Proyecto p WHERE p.nombre=:P_NOMBRE").setParameter("P_NOMBRE", "Proyecto X1").getSingleResult();
        assertEquals(p1.getNombre(), resultPry1.getNombre());
        assertEquals(2, resultPry1.getEmpleados().size());
        Proyecto resultPry2 = (Proyecto) em2.createQuery("SELECT p FROM Proyecto p WHERE p.nombre=:P_NOMBRE").setParameter("P_NOMBRE", "Proyecto X2").getSingleResult();
        assertEquals(p2.getNombre(), resultPry2.getNombre());
        assertEquals(1, resultPry2.getEmpleados().size());
        Empleado empleado01 = (Empleado) em2.createQuery("SELECT p FROM Empleado p WHERE p.nombre=:P_NOMBRE").setParameter("P_NOMBRE", "Empleado01").getSingleResult();
        assertEquals(1, empleado01.getProyectosAsignados().size());
        Empleado empleado02 = (Empleado) em2.createQuery("SELECT p FROM Empleado p WHERE p.nombre=:P_NOMBRE").setParameter("P_NOMBRE", "Empleado02").getSingleResult();
        assertEquals(2, empleado02.getProyectosAsignados().size());

        
        
    }
    
}

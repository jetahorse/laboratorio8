/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import utn.frsf.ofa.cursojava.dao.ConexionJPA;


/**
 *
 * @author federicoaugustotschopp
 */
public class ProyectoDaoJPA implements ProyectoDAO{
    private EntityManager em;
    
    @Override
    public void crear(Proyecto e) {
        this.em=ConexionJPA.get();
        try{
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            
        }
        catch(Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
            
        }
        finally{
           em.close();
        }
    }

    @Override
    public void actualizar(Proyecto e) {
        em=ConexionJPA.get();
        try{
            em.getTransaction().begin();
            Proyecto e3=em.find(Proyecto.class, e.getId());
            if (e3!=null){
               em.persist(e3);
            
               e3.setNombre(e.getNombre());
               e3.setDescripcion(e.getDescripcion());
               e3.setPresupuestoMaximo(e.getPresupuestoMaximo());
               
            }
            
            em.getTransaction().commit();
            
        } catch (Exception ex){
          ex.printStackTrace();
          em.getTransaction().rollback();
        }
        finally{
            em.close();
        }
    }

    @Override
    public void eliminar(Proyecto e) {
        em=ConexionJPA.get();
        try{
            em.getTransaction().begin();
            Proyecto e1=em.find(Proyecto.class,e.getId());
            if (e1!=null){
                em.remove(e1);
            }
            
            /*Query q=em.createQuery("DELETE p FROM Proyecto p WHERE p.");
            q.setParameter("actualId", e.getId());
            int filasEliminadas=q.executeUpdate();*/
            
            em.getTransaction().commit();
            
            
        } catch (Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
        finally{
            em.close();
        }
        
    }

    @Override
    public Proyecto buscarPorId(Integer num) {
        Proyecto proy=null;
        em=ConexionJPA.get();
        try{
            em.getTransaction().begin();
            proy=(Proyecto) em.createQuery("SELECT p FROM Proyecto p WHERE p.id="+Integer.toString(num)).getSingleResult();
            em.getTransaction().commit();
            
        } catch(Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
        finally{
            em.close();
        }
        return proy;
    }

    @Override
    public List<Proyecto> buscarTodos() {
        em=ConexionJPA.get();
        List<Proyecto> resultado=new ArrayList<>();
        try{
            em.getTransaction().begin();
            resultado=em.createQuery("SELECT p FROM Proyecto p").getResultList();
            em.getTransaction().commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
        finally{
            em.close();
        }
        return resultado;
    }

   
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.apartmatesws.service;

import com.mycompany.apartmatesws.Roommate;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author megh
 */
@Stateless
@Path("roommates")
public class RoommateFacadeREST extends AbstractFacade<Roommate> {

    @PersistenceContext(unitName = "com.mycompany_ApartmatesWS_war_1.0PU")
    private EntityManager em;

    public RoommateFacadeREST() {
        super(Roommate.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Roommate entity) {
        super.create(entity);
    }
    
        @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /*
    -------------------------------------------------------------------------------------------
    This method returns a JSON file containing the total number of Roommates in the database
    // e.g., http://venus.cs.vt.edu/ApartmatesWS/webresources/roommates/count
    -------------------------------------------------------------------------------------------
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    /*
    ------------------------------------------------------------------------------------
    This method returns a JSON file containing all of the data about the Roommate
    whose name email is given in the url.
    e.g., http://venus.cs.vt.edu/ApartmatesWS/webresources/roommates/email/roommate1@apt.com
    ------------------------------------------------------------------------------------
     */
    @GET
    @Path("/email/{email}")
    @Produces({MediaType.TEXT_PLAIN})
    public String findByEmail(@PathParam("email") String email) {
        try {
            TypedQuery<Roommate> query = em.createNamedQuery("Roommate.findByEmail", Roommate.class);
            Roommate r = query.setParameter("email", email).getSingleResult();
            return String.valueOf(r.getPoints());

        } catch (Exception e) {
            return "Roommate not found";
        }
    }

}

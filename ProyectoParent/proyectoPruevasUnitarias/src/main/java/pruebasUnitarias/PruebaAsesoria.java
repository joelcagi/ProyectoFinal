package pruebasUnitarias;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import entidadesProyecto.Asesoria;
import entidadesProyecto.Asignatura;
import entidadesProyecto.Director;
import entidadesProyecto.Docente;
import entidadesProyecto.Persona;
import entidadesProyecto.Programa;

@RunWith(Arquillian.class)
public class PruebaAsesoria {

	@PersistenceContext
	private EntityManager entityManager;

	private Asesoria asesoria = new Asesoria();
	private Asignatura asignatura = new Asignatura();
	private Director director = new Director();
	private Programa programa = new Programa();

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(Persona.class, Asesoria.class, Asignatura.class)
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertTest() {
		
		director.setId("1");
		director.setNombre("john");
		director.setApellido("calderon");
		director.setEdad("27");
		director.setUsuario("joncito");
		director.setContrasenia("12345");
		director.
		
		
		programa.setNombre("ingenieria");
		programa.setId("1");
		programa.setDirector(director);
		
		asignatura.setId("1");
		asignatura.setNombre("analisis");
		asignatura.setHorario("hoy");
		asignatura.setDirector(director);
		

		//
		// entityManager.persist(docente);
		// Docente personaRegistrada = entityManager.find(Docente.class,
		// docente.getId());
		// Assert.assertEquals(docente, personaRegistrada);

	}

}

package pruebasUnitarias;

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

import entidadesProyecto.Docente;
import entidadesProyecto.Estudiante;
import entidadesProyecto.Persona;

@RunWith(Arquillian.class)

public class PruebaAlumno {
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private Estudiante esttudiante= new Estudiante();
	

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(Persona.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertTest() {
		esttudiante.setNombre("hector ");
		esttudiante.setApellido("robledo");
		esttudiante.setEdad("25");
		esttudiante.setUsuario("hector2645");
		esttudiante.setContrasenia("123456");
		esttudiante.setDireccion("pinares");
		esttudiante.setTelefono("e1312");
		esttudiante.setId("1");

		entityManager.persist(esttudiante);
		Estudiante personaRegistrada = entityManager.find(Estudiante.class, esttudiante.getId());
		Assert.assertEquals(esttudiante, personaRegistrada);

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void updateTest() {
		esttudiante.setNombre("hector");
		esttudiante.setApellido("robledo");
		esttudiante.setEdad("25");
		esttudiante.setUsuario("hector2645");
		esttudiante.setContrasenia("123456");
		esttudiante.setDireccion("pianres");
		esttudiante.setTelefono("e1312");
		esttudiante.setId("2");

		entityManager.persist(esttudiante);

		Estudiante personaRegistrada = entityManager.find(Estudiante.class, esttudiante.getId());
		personaRegistrada.setApellido("raul iño");
		entityManager.merge(personaRegistrada);
		Estudiante personaActualizada = entityManager.find(Estudiante.class, personaRegistrada.getId());
		Assert.assertEquals(personaActualizada, personaRegistrada);
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void dropTest() {
		esttudiante.setNombre("hector ");
		esttudiante.setApellido("robledo");
		esttudiante.setEdad("25");
		esttudiante.setUsuario("hector2645");
		esttudiante.setContrasenia("123456");
		esttudiante.setDireccion("pianres");
		esttudiante.setTelefono("e1312");
		esttudiante.setId("1");

		entityManager.persist(esttudiante);
		Estudiante eliminarPersona = entityManager.find(Estudiante.class, esttudiante.getId());
		Assert.assertEquals(esttudiante, eliminarPersona);
		entityManager.remove(eliminarPersona);

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void consultTest() {
		insertTest();
		Estudiante consulta = entityManager.find(Estudiante.class, esttudiante.getId());
		Assert.assertEquals("1", esttudiante.getId());

	}

}

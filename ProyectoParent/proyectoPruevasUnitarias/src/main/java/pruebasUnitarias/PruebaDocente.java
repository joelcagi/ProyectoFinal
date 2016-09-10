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
import entidadesProyecto.Persona;

@RunWith(Arquillian.class)
public class PruebaDocente {

	@PersistenceContext
	private EntityManager entityManager;

	private Docente docente = new Docente();

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(Persona.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertTest() {
		docente.setNombre("hector ");
		docente.setApellido("robledo");
		docente.setEdad("25");
		docente.setUsuario("hector2645");
		docente.setContrasenia("123456");
		docente.setDireccion("pianres");
		docente.setTelefono("e1312");
		docente.setId("1");

		entityManager.persist(docente);
		Docente personaRegistrada = entityManager.find(Docente.class, docente.getId());
		Assert.assertEquals(docente, personaRegistrada);

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void updateTest() {
		docente.setNombre("hector");
		docente.setApellido("robledo");
		docente.setEdad("25");
		docente.setUsuario("hector2645");
		docente.setContrasenia("123456");
		docente.setDireccion("pianres");
		docente.setTelefono("e1312");
		docente.setId("2");

		entityManager.persist(docente);

		Docente personaRegistrada = entityManager.find(Docente.class, docente.getId());
		personaRegistrada.setApellido("raul iño");
		entityManager.merge(personaRegistrada);
		Docente personaActualizada = entityManager.find(Docente.class, personaRegistrada.getId());
		Assert.assertEquals(personaActualizada, personaRegistrada);
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void dropTest() {
		docente.setNombre("hector ");
		docente.setApellido("robledo");
		docente.setEdad("25");
		docente.setUsuario("hector2645");
		docente.setContrasenia("123456");
		docente.setDireccion("pianres");
		docente.setTelefono("e1312");
		docente.setId("1");

		entityManager.persist(docente);
		Docente eliminarPersona = entityManager.find(Docente.class, docente.getId());
		Assert.assertEquals(docente, eliminarPersona);
		entityManager.remove(eliminarPersona);

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	public void consultTest() {
		insertTest();
		Docente consulta = entityManager.find(Docente.class, docente.getId());
		Assert.assertEquals("1", docente.getId());

	}

}

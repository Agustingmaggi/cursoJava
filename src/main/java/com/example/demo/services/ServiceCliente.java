import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class ServiceCliente {

    @Autowired
    private RepositoryCliente repo;

    public ClienteInfo obtenerInfoCliente(Long id) {
        ModeloCliente cliente = repo.findById(id).orElse(null);

        if (cliente == null) {
            // Manejar el caso donde el cliente no existe
            return null;
        }

        LocalDate fechaNacimiento = cliente.getFechaNacimiento();
        int edad = calcularEdad(fechaNacimiento);

        ClienteInfo clienteInfo = new ClienteInfo();
        clienteInfo.setNombre(cliente.getNombre());
        clienteInfo.setApellido(cliente.getApellido());
        clienteInfo.setEdad(edad);

        return clienteInfo;
    }

    private int calcularEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            return 0; // O algún valor por defecto
        }

        LocalDate fechaActual = LocalDate.now();
        return Period.between(fechaNacimiento, fechaActual).getYears();
    }
}
package cloud.reciclarg.service;

import cloud.reciclarg.model.User;
import cloud.reciclarg.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
La clase UserService es un componente de servicio de Spring, 
que se utiliza para realizar operaciones relacionadas con usuarios.
La anotación @Service indica que esta clase es un componente de servicio 
y permite que Spring la administre y la inyecte en otras clases donde sea necesario.
 */
@Service
public class UserService implements IUserService {

    /*
      La anotación @Autowired se utiliza para inyectar una dependencia de la clase UserRepository 
    en la variable UserRepo. 
     */
    @Autowired
    /*
    El UserRepository es una interfaz que define métodos para 
    acceder y manipular datos de usuarios en una base de datos.
     */
    public UserRepository UserRepo;

    /*
        El método buscarUserByName busca un usuario por su nombre de usuario. 
     */
    @Override
    public User buscarUserByName(String username) throws Exception {

        /*
        Utiliza el método findByUsername del UserRepo para obtener un Optional que 
        contiene el usuario correspondiente al nombre de usuario proporcionado.
         */
        Optional<User> response = Optional.of(UserRepo.findByUsername(username));
        if (response.isPresent()) {

            /* 
            Luego, se verifica si el Optional tiene un valor utilizando el método isPresent(). 
            Si está presente, se obtiene el usuario usando el método get() y se devuelve.
             */
            User newUser = response.get();
            return newUser;
        } else {
            throw new Exception("No se encontró el usuario. Ingrese un nombre de usuario correcto.");
            //Si el Optional no tiene un valor, se lanza una excepción informando que el usuario no se encontró.

        }

    }

    @Override
    public void SaveUsuario(User user) throws Exception {

        /*
        El método SaveUsuario guarda un nuevo usuario en la base de datos, 
        pero primero verifica si ya existe un usuario con el mismo correo electrónico 
        utilizando el método findByEmail del UserRepo.
         */
        Optional<User> response = Optional.of(UserRepo.findByEmail(user.getEmail()));
        if (response.isPresent()) {
            /*
            Si el Optional tiene un valor, significa que ya existe un usuario con el mismo correo electrónico
            y se lanza una excepción.
             */
            System.out.println("El usuario ya existe.");
            throw new Exception("El usuario ya existe.");
        } else {
            /*
            En caso contratio, se ejecutan algunas acciones adicionales para validar
            que todos los campos obligatorios existan y en caso de pasar las validaciones
            correctamente, el nuevo usuario se guardarà en la base de datos.
             */
            User newUser = response.get();
            validarUsuario(newUser.getNombre(), newUser.getApellido(), newUser.getUsername(),
                    newUser.getEmail(), newUser.getPassword());
            UserRepo.save(user);
        }

    }

    /*
    El método borrarUsuario recibe un parámetro id que representa el ID del usuario que se desea eliminar.
     */
    @Override
    public void borrarUsuario(Long id) throws Exception {
        /*
        Utiliza el método findById del UserRepo para buscar el usuario en la base de datos.
        El resultado se envuelve en un Optional.
         */
        Optional<User> response = Optional.of(UserRepo.findById(id).orElse(null));
        if (response.isPresent()) {
            /*
            Si el Optional tiene un valor (es decir, se encontró el usuario correspondiente 
            al ID proporcionado), se obtiene el usuario usando el método get().
             */
            User eliminarUsuario = response.get();
            /*
            A continuación, se utiliza el método deleteById del UserRepo para eliminar el usuario de la base de datos.
             */
            UserRepo.deleteById(eliminarUsuario.getId());
        } else {
            /*
            Si el Optional no tiene un valor, se lanza una excepción indicando que el usuario es inexistente.
             */
            throw new Exception("El usuario es inexistente.");
        }
    }

    /*
        El método buscarUserById busca un usuario por su ID utilizando el método findById del UserRepo.
     */
    @Override
    public User buscarUserById(Long id) throws Exception {
        Optional<User> response = Optional.of(UserRepo.findById(id).orElse(null));
        if (response.isPresent()) {
            /*
            Si el Optional tiene un valor, se obtiene el usuario utilizando el método get() y se devuelve.
             */
            User usuarioBuscado = response.get();
            return usuarioBuscado;
        } else {
            /*
            Si el Optional no tiene un valor, se lanza una excepción informando que no se encontró el usuario.
             */
            throw new Exception("No se encontró el usuario. Ingrese un id válido.");
        }
    }

    /*
    El método validarUsuario se utiliza para realizar validaciones en los campos del usuario.
    Verifica si los campos nombre, apellido, username, email y password están vacíos.
    Si alguno de los campos está vacío, se lanza una excepción con un mensaje específico indicando qué campo es obligatorio.
     */
    public void validarUsuario(String nombre, String apellido, String username, String email, String password) throws Exception {
        if (nombre.isEmpty()) {
            throw new Exception("El nombre del usuario es nulo. Este campo es obligatorio.");
        }

        if (apellido.isEmpty()) {
            throw new Exception("El apellido del usuario es nulo. El campo es obligatorio.");
        }

        if (username.isEmpty()) {
            throw new Exception("No existe un nombre de usuario. Ingrese un valor.");
        }

        if (email.isEmpty()) {
            throw new Exception("El campo email es obligatorio. Ingrese un email válido.");
        }

        if (password.isEmpty()) {
            throw new Exception("Debe ingresar una contraseña. Por favor ingrese una.");
        }
    }
}

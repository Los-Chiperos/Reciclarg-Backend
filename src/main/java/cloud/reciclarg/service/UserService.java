package cloud.reciclarg.service;

import cloud.reciclarg.model.User;
import cloud.reciclarg.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    public UserRepository UserRepo;

    @Override
    public User buscarUserByName(String username) throws Exception {
        Optional<User> response = Optional.of(UserRepo.findByUsername(username));
        if (response.isPresent()) {
            User newUser = response.get();
            return newUser;
        } else {
            throw new Exception("No se encontró el usuario. Ingrese un nombre de usuario correcto.");
        }
    }

    @Override
    public void SaveUsuario(User user) throws Exception {

        Optional<User> response = Optional.of(UserRepo.findByEmail(user.getEmail()));
        if (response.isPresent()) {
            User newUser = response.get();
            validarUsuario(newUser.getNombre(), newUser.getApellido(), newUser.getUsername(),
                    newUser.getEmail(), newUser.getPassword());
            System.out.println("El usuario ya existe.");
            throw new Exception("El usuario ya existe.");
        } else {
            UserRepo.save(user);
        }

    }

    @Override
    public void borrarUsuario(Long id) throws Exception {
        Optional<User> response = Optional.of(UserRepo.findById(id).orElse(null));
        if (response.isPresent()) {
            User eliminarUsuario = response.get();
            UserRepo.deleteById(eliminarUsuario.getId());
        } else {
            throw new Exception("El usuario es inexistente.");
        }
    }

    @Override
    public User buscarUserById(Long id) throws Exception {
        Optional<User> response = Optional.of(UserRepo.findById(id).orElse(null));
        if (response.isPresent()) {
            User usuarioBuscado = response.get();
            return usuarioBuscado;
        } else {
            throw new Exception("No se encontró el usuario. Ingrese un id válido.");
        }
    }

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

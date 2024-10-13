package br.com.springboot.cursojdev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.cursojdev.model.Usuario;
import br.com.springboot.cursojdev.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	@GetMapping(value = "listartodos")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listaUsuario(){
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@PostMapping(value = "salvar")
	@ResponseBody
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
		
		Usuario user = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping(value = "delete")
	@ResponseBody
	public ResponseEntity<String> delete(@RequestParam Long id){
		
		usuarioRepository.deleteById(id);
		
		return new ResponseEntity<String>("User deletado", HttpStatus.OK);
	}
	
	@GetMapping("buscarPorId")
	@ResponseBody
	public ResponseEntity<Usuario> consultarUserId(@RequestParam(name = "iduser") Long iduser){
		
		Usuario user;
		
		user = usuarioRepository.findById(iduser).get();
		
		return new ResponseEntity<Usuario>(user , HttpStatus.OK);
		
	}
	@PutMapping("atualizaruser")
	@ResponseBody
	public ResponseEntity<?> atualizarUser(@RequestBody Usuario usuario){
		 
		if(usuario.getId() == null) {
			
			return new ResponseEntity<String>("Id não pode ser nulo", HttpStatus.OK);
		}
		
		Usuario user = usuarioRepository.saveAndFlush(usuario);
		
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}
	
	@GetMapping("buscapornome")
	@ResponseBody
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam String nome){
		
		List<Usuario> users = usuarioRepository.consultaPorNome(nome.trim().toUpperCase());
		
		return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
	}
	
    @RequestMapping(value = "/mostranome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(name);
    	usuarioRepository.save(usuario);
    	
        return "Hello " + name + "!";
    }
}

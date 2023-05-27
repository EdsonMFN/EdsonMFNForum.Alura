package ForumAlura.api.controller;

import ForumAlura.api.entity.login.DadosAutentticacao;
import ForumAlura.api.entity.login.LoginUsuario;
import ForumAlura.api.service.service.DadosToken;
import ForumAlura.api.service.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager meneger;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin (@RequestBody @Valid DadosAutentticacao dados){
        var autenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

        var authentication = meneger.authenticate(autenticationToken);


        var token = tokenService.gerarToken((LoginUsuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosToken(token));
    }
}

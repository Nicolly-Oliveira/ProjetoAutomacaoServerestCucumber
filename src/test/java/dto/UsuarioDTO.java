package dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {

    private String nome;
    private String email;
    private String password;
    private String administrador;
    private String id;

    // Construtor privado que é chamado pelo Builder
    private UsuarioDTO(Builder builder) {
        this.nome = builder.nome;
        this.email = builder.email;
        this.password = builder.password;
        this.administrador = builder.administrador;
        this.id = builder.id;
    }

    // Getters e setters (incluindo setId e getId)
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getAdministrador() { return administrador; }
    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    // Builder
    public static class Builder {
        private String nome;
        private String email;
        private String password;
        private String administrador;
        private String id;

        public Builder() {

        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder administrador(String administrador) {
            this.administrador = administrador;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public UsuarioDTO build() {
            return new UsuarioDTO(this);
        }
    }
}

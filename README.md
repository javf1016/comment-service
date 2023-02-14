# comment-service
Comment service eureka


EndPoints

Crear un comentario 
* http://localhost:9003/comments/202/comments
* Body: {
  "authorId": 551,
  "body": "Este es un comentario de prueba sola"
}
* Para crear el comentario se agrega una busqueda del Usuario con FEIGN 
* Para crear el comentario se agrega una busqueda del Post con FEIGN 
* Usa retry cuando el USER-SERVICE o POST-SERVICE estan abajo

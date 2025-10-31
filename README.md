Proyecto final de Programación III - Cliente de GitHub con interfaz JavaFX.

---
##  Cómo ejecutar

**Versión de Java:** 21  
**Tipo de proyecto:** JavaFX (Interfaz Gráfica)

### En NetBeans:
1. Abrir el proyecto en NetBeans.
2. Limpiar y compilar (`Clean and Build`).
3. Ejecutar con `Run Project (F6)`.


### **Descripción de endpoints usados**
##  Endpoints de la API de GitHub

 **GET** https://api.github.com/users/{username}  
  → Devuelve la información del perfil del usuario.

 **GET** https://api.github.com/users/{username}/repos  
  → Devuelve la lista de repositorios públicos del usuario.


## 🖼️ Ejemplo de interfaz

<img width="956" height="595" alt="imagenProyecto" src="https://github.com/user-attachments/assets/132b421a-4ae6-4233-99e8-435dac29bda4" />

##  Notas

- La API pública de GitHub permite hasta **60 solicitudes por hora** sin autenticación.  
- Si necesitas más, puedes usar un token personal:
  - Crear un token en [GitHub Settings → Developer Settings → Tokens].
  - Agregarlo como variable de entorno:


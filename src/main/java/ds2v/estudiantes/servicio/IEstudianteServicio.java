package ds2v.estudiantes.servicio;

import ds2v.estudiantes.modelo.Estudiante;

import java.util.List;

public interface IEstudianteServicio {

    public List<Estudiante> listarEstudiante();

    public Estudiante buscarEstudiantePorId(Integer idEstudiante);

    // Uso del metodo para guardar/modificar un estudiante
    public void guardarEstudiante(Estudiante estudiante);

    public void eliminarEstudiante(Estudiante estudiante);
}

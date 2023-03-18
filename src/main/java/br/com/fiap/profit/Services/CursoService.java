package br.com.fiap.profit.Services;

import org.springframework.stereotype.Service;

@Service
public class CursoService {
    // private List<Curso> cursos = new ArrayList<>();

    // public Curso cadastrarCurso(Curso curso){
    //     cursos.add(curso);
    //     return curso;
    // }

    // public List<Curso> listarCurso(){
    //     return cursos;
    // }

    // public Curso buscarPorId(Integer id){
    //     return cursos.stream().filter(curso -> curso.getId().equals(id)).findFirst().orElse(null);
    // }

    // public Curso atualizarCurso(Curso curso){
    //     Curso cursoExistente = buscarPorId(curso.getId());
    //     if(cursoExistente != null){
    //         cursos.set(cursos.indexOf(cursoExistente), curso);
    //         return curso;
    //     }
    //     return null;
    // }

    // public void deletarCurso(Integer id){
    //     Curso cursoExistente = buscarPorId(id);
    //     if(cursoExistente != null){
    //         cursos.remove(cursoExistente);
    //     }
    // }
}

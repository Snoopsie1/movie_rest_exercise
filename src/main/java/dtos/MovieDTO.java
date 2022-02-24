/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tha
 *
 */
public class MovieDTO
{
    private long id;
    private String title;
    private String director;

    public MovieDTO(String title, String director) {
        this.title = title;
        this.director = director;
    }
    
    public static List<MovieDTO> getDtos(List<Movie> rms){
        List<MovieDTO> rmdtos = new ArrayList();
        rms.forEach(rm->rmdtos.add(new MovieDTO(rm)));
        return rmdtos;
    }


    public MovieDTO(Movie rm) {
        if(rm.getId() != null)
            this.id = rm.getId();

        this.title = rm.getTitle();
        this.director = rm.getDirector();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String dummyStr1) {
        this.title = dummyStr1;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String dummyStr2) {
        this.director = dummyStr2;
    }

    @Override
    public String toString() {
        return "RenameMeDTO{" + "id=" + id + ", title=" + title + ", director=" + director + '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDTO movieDTO = (MovieDTO) o;
        return Objects.equals(title, movieDTO.title) && Objects.equals(director, movieDTO.director);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(title, director);
    }
}

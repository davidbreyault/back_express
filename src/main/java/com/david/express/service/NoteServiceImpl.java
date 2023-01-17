package com.david.express.service;

import com.david.express.exception.ResourceNotFoundException;
import com.david.express.model.Note;
import com.david.express.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public Optional<Note> findNoteById(Long id) throws ResourceNotFoundException {
        Optional<Note> note = noteRepository.findById(id);
        if (!note.isPresent()) {
            throw new ResourceNotFoundException("Note not found with id : " + id);
        }
        return note;
    }

    @Override
    public Note save(Note note) {
        noteRepository.save(note);
        return note;
    }

    @Override
    public void deleteNoteById(Long id) throws ResourceNotFoundException {
        Optional<Note> note = findNoteById(id);
        if (!note.isPresent()) {
            throw new ResourceNotFoundException("Note not found with id : " + id);
        }
        noteRepository.deleteById(note.get().getId());
    }
}

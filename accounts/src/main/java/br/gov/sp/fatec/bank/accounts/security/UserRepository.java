package br.gov.sp.fatec.bank.accounts.security;

import br.gov.sp.fatec.bank.users.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary // Workaround. This should not be done like this
public class UserRepository implements br.gov.sp.fatec.bank.users.repository.UserRepository {
	@Override
	public User findByUsername(String username) {
		return null;
	}

	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public List<User> findAll(Sort sort) {
		return null;
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public List<User> findAll(Iterable<Long> iterable) {
		return null;
	}

	@Override
	public long count() {
		return 0;
	}

	@Override
	public void delete(Long aLong) {

	}

	@Override
	public void delete(User user) {

	}

	@Override
	public void delete(Iterable<? extends User> iterable) {

	}

	@Override
	public void deleteAll() {

	}

	@Override
	public <S extends User> S save(S s) {
		return null;
	}

	@Override
	public <S extends User> List<S> save(Iterable<S> iterable) {
		return null;
	}

	@Override
	public User findOne(Long aLong) {
		return null;
	}

	@Override
	public boolean exists(Long aLong) {
		return false;
	}

	@Override
	public void flush() {

	}

	@Override
	public <S extends User> S saveAndFlush(S s) {
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<User> iterable) {

	}

	@Override
	public void deleteAllInBatch() {

	}

	@Override
	public User getOne(Long aLong) {
		return null;
	}

	@Override
	public <S extends User> S findOne(Example<S> example) {
		return null;
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> example) {
		return null;
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
		return null;
	}

	@Override
	public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
		return null;
	}

	@Override
	public <S extends User> long count(Example<S> example) {
		return 0;
	}

	@Override
	public <S extends User> boolean exists(Example<S> example) {
		return false;
	}
}

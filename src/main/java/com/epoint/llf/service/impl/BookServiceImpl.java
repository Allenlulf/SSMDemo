package com.epoint.llf.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epoint.exception.AppointException;
import com.epoint.exception.NoNumberException;
import com.epoint.exception.RepeatAppointException;
import com.epoint.llf.dao.AppointmentDao;
import com.epoint.llf.dao.BookDao;
import com.epoint.llf.dto.AppointExecution;
import com.epoint.llf.entity.Appointment;
import com.epoint.llf.entity.Book;
import com.epoint.llf.enums.AppointStateEnum;
import com.epoint.llf.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookDao bookDao;

	@Autowired
	private AppointmentDao appointmentDao;

	@Override
	public Book getById(long bookId) {
		return bookDao.queryById(bookId);
	}

	@Override
	public List<Book> getList() {
		return bookDao.queryAll(0, 1000);
	}

	@Override
	@Transactional
	/**
	 * ʹ��ע��������񷽷����ŵ㣺 1.�����ŶӴ��һ��Լ������ȷ��ע���񷽷��ı�̷��
	 * 2.��֤���񷽷���ִ��ʱ�価���̣ܶ���Ҫ�����������������RPC/HTTP������߰��뵽���񷽷��ⲿ
	 * 3.�������еķ�������Ҫ������ֻ��һ���޸Ĳ�����ֻ����������Ҫ�������
	 */
	public AppointExecution appoint(long bookId, long studentId) {
		try {
			// �����
			int update = bookDao.reduceNumber(bookId);
			if (update <= 0) { // ��治��
				throw new NoNumberException("no number");
			} else {
				// ִ��ԤԼ����
				int insert = appointmentDao.insertAppointment(bookId, studentId);
				if (insert <= 0) {// �ظ�ԤԼ
					throw new RepeatAppointException("repeat appoint");
				} else {
					Appointment appointment = appointmentDao.queryByKeyWithBook(bookId, studentId);
					return new AppointExecution(bookId, AppointStateEnum.SUCCESS, appointment);
				}
			}
			// Ҫ����catch
			// Exception�쳣ǰ��catchס���׳�����Ȼ�Զ�����쳣Ҳ�ᱻת��ΪAppointException�����¿��Ʋ��޷�����ʶ�����ĸ��쳣
		} catch (NoNumberException e1) {
			throw e1;
		} catch (RepeatAppointException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// ���б������쳣ת��Ϊ�������쳣
			throw new AppointException("appoint inner error:" + e.getMessage());
		}
	}
}
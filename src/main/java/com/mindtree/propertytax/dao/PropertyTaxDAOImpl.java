package com.mindtree.propertytax.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Cacheable;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.propertytax.model.PropertyDetails;
import com.mindtree.propertytax.model.PropertyMaster;
import com.mindtree.propertytax.model.UserDetails;

/**
 * @author M1026220
 * 
 *         This is a Repository class which is used to Persist the Object and
 *         fetch Data from Database based on certain Criteria.
 *
 */

@Repository
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class PropertyTaxDAOImpl implements PropertyTaxDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Logger logger = Logger.getLogger(PropertyTaxDAOImpl.class);

	/**
	 * This method is to fetch Zone by filtering all the duplicate Zone and returns
	 * the List containing the Zones.
	 * 
	 * @return List
	 */

	@SuppressWarnings("unchecked")
	public List<String> getPropertyZoneDetails() {

		logger.info("Inside getPropertyZoneDetails Method of PropertyTaxDAOImpl");

		List<String> propertyZoneList = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(PropertyMaster.class);
			criteria.setProjection(Projections.distinct(Projections.property("zoneType")));
			propertyZoneList = criteria.list();
			transaction.commit();
		} catch (HibernateException hibernateException) {
			if (transaction != null)
				transaction.rollback();
			logger.error(hibernateException.getMessage());
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		} finally {
			session.close();
		}
		logger.info("PropertyZoneDetails Retreived Successfully");
		return propertyZoneList;
	}

	/**
	 * 
	 * This method is to fetch PropertyDescription by filtering all the duplicate
	 * PropertyDescription and returns the List containing the PropertyDescription.
	 * 
	 * 
	 * @return List
	 */

	@SuppressWarnings("unchecked")
	public List<String> getPropertyDescriptionDetails() {

		logger.info("Inside getPropertyDescriptionDetails Method of PropertyTaxDAOImpl");

		List<String> propertyDescriptionList = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(PropertyMaster.class);
			criteria.setProjection(Projections.distinct(Projections.property("propertyDescription")));
			propertyDescriptionList = criteria.list();
			transaction.commit();
		} catch (HibernateException hibernateException) {
			if (transaction != null)
				transaction.rollback();
			logger.error(hibernateException.getMessage());
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		} finally {
			session.close();
		}
		logger.info("PropertyDescriptionDetails Retreived Successfully");
		return propertyDescriptionList;
	}

	/**
	 * 
	 * This method is to fetch PropertyStatus by filtering all the duplicate
	 * PropertyStatus and returns the List containing the PropertyStatus.
	 * 
	 * 
	 * @return List
	 */

	@SuppressWarnings("unchecked")
	public List<String> getPropertyStatusDetails() {

		logger.info("Inside getPropertyStatusDetails Method of PropertyTaxDAOImpl");

		List<String> propertyStatusList = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(PropertyMaster.class);
			criteria.setProjection(Projections.distinct(Projections.property("propertyStatus")));
			propertyStatusList = criteria.list();
		} catch (HibernateException hibernateException) {
			if (transaction != null)
				transaction.rollback();
			logger.error(hibernateException.getMessage());
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		} finally {
			session.close();
		}

		logger.info("PropertyStatusDetails Retreived Successfully");
		return propertyStatusList;
	}

	/**
	 * This method is Save UserDetails and PropertyDetails to Database.
	 * 
	 * 
	 */

	public void savePropertyDetails(UserDetails userDetails, PropertyDetails propertDetails) {

		logger.info("Inside savePropertyDetails Method of PropertyTaxDAOImpl");

		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(userDetails);
			session.saveOrUpdate(propertDetails);
			transaction.commit();

		} catch (HibernateException hibernateException) {
			if (transaction != null)
				transaction.rollback();
			logger.error(hibernateException.getMessage());
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		} finally {
			session.close();
		}
		logger.info("PropertyDetails Saved Successfully");

	}

	/**
	 * This method will accept ZoneList containing Zones and PropertyStatusList
	 * containing PropertyStatus as arguments and return the Map consisting of
	 * report of each zone.
	 * 
	 * @return HashMap
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, HashMap<String, Double>> getZoneWiseReport(List<String> zoneList,
			List<String> propertyStatusList) {

		logger.info("Inside getZoneWiseReport Method of PropertyTaxDAOImpl");
		HashMap<String, HashMap<String, Double>> zoneMap = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			zoneMap = new HashMap<String, HashMap<String, Double>>();
			for (int i = 0; i < zoneList.size(); i++) {
				String zone = zoneList.get(i);
				HashMap<String, Double> propertiesStatusMap = new HashMap<String, Double>();
				for (int j = 0; j < propertyStatusList.size(); j++) {
					String propertyStatus = (String) propertyStatusList.get(j);
					Criteria criteria = session.createCriteria(PropertyDetails.class);
					criteria.add(Restrictions.eq("zoneType", zone));
					criteria.add(Restrictions.eq("propertyStatus", propertyStatus));
					criteria.setProjection(Projections.sum("totalTax"));
					List<Double> list = criteria.list();
					propertiesStatusMap.put(propertyStatus, list.get(0));
				}
				if (zoneMap.get(zone) == null) {
					zoneMap.put(zone, propertiesStatusMap);
				}
			}
			transaction.commit();
		} catch (HibernateException hibernateException) {
			if (transaction != null)
				transaction.rollback();
			logger.error(hibernateException.getMessage());
		} catch (NullPointerException nullPointerException) {
			logger.error(nullPointerException.getMessage());
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		} finally {
			session.close();
		}

		logger.info("ZoneWiseReport Retreived Successfully");
		return zoneMap;
	}

	/**
	 * this method will accepts zoneType,propertyDescription,propertyStatus as
	 * arguments and returns the ZoneValue.
	 * 
	 * 
	 * @return Double
	 */

	public Double getZoneValue(String zoneType, String propertyDescription, String propertyStatus) {

		logger.info("Inside getZoneValue Method of PropertyTaxDAOImpl");
		PropertyMaster propertyMaster = null;
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(PropertyMaster.class);
			criteria.add(Restrictions.eq("propertyDescription", propertyDescription));
			criteria.add(Restrictions.eq("propertyStatus", propertyStatus));
			criteria.add(Restrictions.eq("zoneType", zoneType));
			List<PropertyMaster> propertyMasterList = criteria.list();
			propertyMaster = propertyMasterList.get(0);
			transaction.commit();
		} catch (HibernateException hibernateException) {
			if (transaction != null)
				transaction.rollback();
			logger.error(hibernateException.getMessage());
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		} finally {
			session.close();
		}
		logger.info("ZoneValue Retreived Successfully" + propertyMaster.getZoneValue());
		return (double) propertyMaster.getZoneValue();

	}
}

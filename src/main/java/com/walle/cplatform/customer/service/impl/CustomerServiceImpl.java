package com.walle.cplatform.customer.service.impl;

import com.walle.cplatform.customer.bean.CustomerBean;
import com.walle.cplatform.customer.mapper.CustomerMapper;
import com.walle.cplatform.customer.pojos.InputCustomerCreate;
import com.walle.cplatform.customer.service.CustomerService;
import com.walle.cplatform.utils.DateTimeUtils;
import com.walle.cplatform.utils.RestResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerMapper mapper) {
        this.customerMapper = mapper;
    }

    @Override
    public String createCustomer(String userId, InputCustomerCreate data) {
        CustomerBean bean = new CustomerBean();
        bean.setUid(userId);
        bean.setNo(data.getNumber());
        bean.setKindergarten(data.getKindergarten());
        bean.setCard_type(data.getCardType());
        bean.setBuy_dt(data.getBuyDate());
        bean.setComment(data.getComment());
        bean.setGift(data.getGift());
        Date date = DateTimeUtils.currentUTC();
        bean.setCreate_dt(date);
        bean.setUpdate_dt(date);
        bean.setDiscount(data.getDiscount());
        bean.setExpire_dt(data.getExpireDate());
        bean.setFee(data.getFee());
        bean.setPrimary_class_hour(data.getPrimary());
        bean.setSecondary_class_hour(data.getSecondary());
        bean.setPrimary_learn(String.join(":", data.getPriLearn()));

        customerMapper.insert(bean);
        return userId;
    }

    @Override
    public RestResultCode updateCustomer(String uid, InputCustomerCreate data) {
        return null;
    }

    @Override
    public CustomerBean getCustomer(String uid) {
        return getCustomerByUid(uid);
    }

    @Override
    public List<CustomerBean> getCustomerList() {
        return customerMapper.selectAll();
    }

    @Override
    public boolean isNumberExists(long num) {
        Example example = new Example(CustomerBean.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("no", num);

        List<CustomerBean> beans = customerMapper.selectByExample(example);

        return beans.size() > 0;
    }

    private CustomerBean getCustomerByUid(String uid) {
        if (StringUtils.isEmpty(uid)) {
            return null;
        }
        CustomerBean bean = new CustomerBean();
        bean.setUid(uid);
        return customerMapper.selectOne(bean);
    }
}

package com.careline.interview.test.service;

import com.careline.interview.test.dao.MemberDAO;
import com.careline.interview.test.model.Member;
import com.careline.interview.test.model.input.ComputeIn;
import com.careline.interview.test.model.output.ComputeOut;
import com.careline.interview.test.utils.ProjectCalcUtils;
import com.careline.interview.test.utils.ProjectDateUtils;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private MemberDAO memberDAO;

  //mission4
  public List<Member> listAllMember(){
    return memberDAO.findAll();
  }

  //mission3
  public void register(Member member) throws Exception {
    memberDAO.save(member);
  }

  //mission2
  public ComputeOut dealWithCompute(ComputeIn computeIn) {
    ComputeOut computeOut = new ComputeOut();
    try {
      List<Integer> operands = ProjectCalcUtils.filterNull(computeIn.getNumList());
      logger.debug("operands : {}", operands);
      final int scale = 2;
      operands = operands.stream().sorted().collect(Collectors.toList());
      final BigDecimal avg = ProjectCalcUtils.avg(operands, scale);
      final BigDecimal sum = ProjectCalcUtils.sum(operands).setScale(scale);
      final BigDecimal max = new BigDecimal(operands.get(operands.size() - 1)).setScale(scale);
      final BigDecimal min = new BigDecimal(operands.get(0)).setScale(scale);
      logger.debug("size:{}, total:{}, avg:{}", operands.size(), sum, avg);
      computeOut.setAvg(avg.floatValue());
      computeOut.setMax(max.floatValue());
      computeOut.setMin(min.floatValue());
      computeOut.setSum(sum.floatValue());
      computeOut.setSortedList(operands);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return computeOut;
  }

  //mission1
  public String formatLocalDateTime() {
    return LocalDateTime.now().format(ProjectDateUtils.getDeafaultFormatter());
  }

}

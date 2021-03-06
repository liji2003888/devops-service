import React, { useEffect } from 'react';
import { Form, Select } from 'choerodon-ui/pro';
import { injectIntl } from 'react-intl';
import { observer } from 'mobx-react-lite';

import MouserOverWrapper from '../../../../../components/MouseOverWrapper';
import { useSelectStore } from './stores';

function BranchEdit() {
  const {
    formatMessage,
    modal,
    handleRefresh,
    optionsDs,
    issueId,
    selectDs,
  } = useSelectStore();

  useEffect(() => {
    optionsDs.query().then(() => {
      issueId ? selectDs.current.set('issueName', issueId) : null;
    });
  }, []);

  const recordData = optionsDs.toData();
  const optionsData = [];
  let issueTypeCode;
  let issueSummry;
  let issueIusseNum;
  let issueIssueId;
  recordData.forEach(item => {
    issueIssueId = item.issueId;
    issueTypeCode = item.typeCode;
    issueSummry = item.summary;
    issueIusseNum = item.issueNum;
    const data = {
      issueId: issueIssueId,
      typeCode: issueTypeCode,
      summary: issueSummry,
      issueNum: issueIusseNum,
    };
    optionsData.push(data);
  });

  /**
   * 创建
   */
  async function handleOk() {
    try {
      if ((await selectDs.submit()) !== false) {
        handleRefresh();
        return true;
      } else {
        return false;
      }
    } catch (e) {
      return false;
    }
  }

  modal.handleOk(() => handleOk());

  // 用于问题名称的渲染函数
  const renderissueName = (typeCode, issueNum, summary) => {
    let mes = '';
    let icon = '';
    let color = '';
    switch (typeCode) {
      case 'story':
        mes = formatMessage({ id: 'branch.issue.story' });
        icon = 'agile_story';
        color = '#00bfa5';
        break;
      case 'bug':
        mes = formatMessage({ id: 'branch.issue.bug' });
        icon = 'agile_fault';
        color = '#f44336';
        break;
      case 'issue_epic':
        mes = formatMessage({ id: 'branch.issue.epic' });
        icon = 'agile_epic';
        color = '#743be7';
        break;
      case 'sub_task':
        mes = formatMessage({ id: 'branch.issue.subtask' });
        icon = 'agile_subtask';
        color = '#4d90fe';
        break;
      default:
        mes = formatMessage({ id: 'branch.issue.task' });
        icon = 'agile_task';
        color = '#4d90fe';
    }
    return (
      <span>
        <div style={{ color }} className="branch-issue">
          <i className={`icon icon-${icon}`} />
        </div>
        <span className="branch-issue-content">
          <span style={{ color: 'rgb(0,0,0,0.65)' }}>{issueNum}</span>
          <MouserOverWrapper
            style={{ display: 'inline-block', verticalAlign: 'sub' }}
            width="350px"
            text={summary}
          >
            {summary}
          </MouserOverWrapper>
        </span>
      </span>
    );
  };

  const issueNameRender = ({ text }) => {
    let renderTypeCode;
    let renderIssueNum;
    let renderSummary;
    optionsData.forEach(item => {
      if (item.issueId === Number(text)) {
        renderTypeCode = item.typeCode;
        renderIssueNum = item.issueNum;
        renderSummary = item.summary;
      }
    });
    return (
      text ? <span>
        {renderissueName(renderTypeCode, renderIssueNum, renderSummary)}
      </span> : null
    );
  };

  const issueNameOptionRender = ({ record }) => {
    const typeCode = record.get('typeCode');
    const issueNum = record.get('issueNum');
    const summary = record.get('summary');
    return (
      <span>
        {renderissueName(typeCode, issueNum, summary)}
      </span>
    );
  };

  return (
    <div style={{ width: '75%' }}>
      <Form dataSet={selectDs}>
        <Select name="issueName" optionRenderer={issueNameOptionRender} renderer={issueNameRender} />
      </Form>
    </div>
  );
}
export default injectIntl(observer(BranchEdit));

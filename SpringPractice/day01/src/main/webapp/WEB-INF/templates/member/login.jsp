<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="actionUrl" value="/member/login" />
<spring:message var="saveEmailTitle" code='이메일_기억하기' />
<h1>
    <spring:message code="로그인" />
</h1>
<form:form method="POST" action="${actionUrl}" autocomplete="off" modelAttribute="requestLogin">
    <dl>
        <dt>
            <spring:message code="이메일" />
        </dt>
        <dd>
            <form:input path="email" />
            <form:errors path="email" element="div" delimiter="" />
        </dd>
    </dl>
    <dl>
        <dt>
            <spring:message code="비밀번호" />
        </dt>
        <dd>
            <form:password path="password" />
            <form:errors path="password" element="div" delimiter="" />
        </dd>
    </dl>
    <div>
        <form:checkbox path="saveEmail" value="true" label="${saveEmailTitle}" />
    </div>
    <!--
        path: 모델 객체의 필드에 바인딩할 경로를 지정합니다. 여기서는 saveEmail 필드에 바인딩됩니다.
        value: 체크박스가 선택되었을 때 전송되는 값을 지정합니다. 여기서는 true 값이 사용됩니다.
    -->
    <form:errors element="div" delimiter=""/>

     <button type="submit">
          <spring:message code="로그인" />
     </button>
</form:form>
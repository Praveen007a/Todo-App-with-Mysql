		<%@ include file="common/header.jspf" %>
		<%@ include file="common/nav.jspf" %>
		<div class="container">
			<h1>Enter Todo Details</h1>
		<form:form method="post" modelAttribute="todo">

				<fieldset class="mb-3">				
					<form:label path="description">Description</form:label>
					<form:input type="text" path="description" required="required"/>
					<form:errors path="description" cssClass="text-warning"/>
				</fieldset>
				
				<fieldset class="mb-3">				
					<form:label path="targertDate">Target Date</form:label>
					<form:input type="text" path="targertDate" required="required"/>
					<form:errors path="targertDate" cssClass="text-warning"/>
				</fieldset>
				

				<form:input type="hidden" path="id"/>

				<form:input type="hidden" path="done"/>

				<input type="submit" class="btn btn-success"/>
			
			</form:form>
			
		</div>
		<%@ include file="common/footer.jspf" %>

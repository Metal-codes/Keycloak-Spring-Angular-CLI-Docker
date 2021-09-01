<#import "template.ftl" as layout>
<@layout.mainLayout active='account' bodyClass='user'; section>

    <div class="row">
            <h2>${msg("editAccountHtmlTitle")}</h2>
        <div class="subtitle">
            <span class="subtitle"><span class="required">*</span> ${msg("requiredFields")}</span>
        </div>
    </div>

    <form action="${url.accountUrl}" class="form-horizontal" method="post">

        <input type="hidden" id="stateChecker" name="stateChecker" value="${stateChecker}">

        <#if !realm.registrationEmailAsUsername>
            <div class="form-group ${messagesPerField.printIfExists('username','has-error')}">
                <div>
                    <label for="username" class="control-label">${msg("username")}</label> <#if realm.editUsernameAllowed><span class="required">*</span></#if>
                </div>

                <div>
                    <input type="text" class="form-control" id="username" name="username" <#if !realm.editUsernameAllowed>disabled="disabled"</#if> value="${(account.username!'')}"/>
                </div>
            </div>
        </#if>

        <div class="form-group ${messagesPerField.printIfExists('email','has-error')}">
            <div>
            <label for="email" class="control-label">${msg("email")}</label> <span class="required">*</span>
            </div>

            <div>
                <input type="text" class="form-control" id="email" name="email" autofocus value="${(account.email!'')}"/>
            </div>
        </div>

        <div class="form-group ${messagesPerField.printIfExists('firstName','has-error')}">
            <div>
                <label for="firstName" class="control-label">${msg("firstName")}</label> <span class="required">*</span>
            </div>

            <div>
                <input type="text" class="form-control" id="firstName" name="firstName" value="${(account.firstName!'')}"/>
            </div>
        </div>

        <div class="form-group ${messagesPerField.printIfExists('lastName','has-error')}">
            <div>
                <label for="lastName" class="control-label">${msg("lastName")}</label> <span class="required">*</span>
            </div>

            <div>
                <input type="text" class="form-control" id="lastName" name="lastName" value="${(account.lastName!'')}"/>
            </div>
        </div>

        <div class="form-group">
            <div id="kc-form-buttons" class="submit">
                <div class="">
                    <#if url.referrerURI??><a href="${url.referrerURI}">${kcSanitize(msg("backToApplication")?no_esc)}</a></#if>
                    <button type="submit" class="${properties.kcButtonClass!} ${properties.kcButtonPrimaryClass!} ${properties.kcButtonLargeClass!}" name="submitAction" value="Save">${msg("doSave")}</button>
                    <button type="submit" class="${properties.kcButtonClass!} ${properties.kcButtonDefaultClass!} ${properties.kcButtonLargeClass!}" name="submitAction" value="Cancel">${msg("doCancel")}</button>
                </div>
            </div>
        </div>
    </form>

</@layout.mainLayout>

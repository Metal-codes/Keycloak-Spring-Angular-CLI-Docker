<#import "template.ftl" as layout>
<@layout.mainLayout active='password' bodyClass='password'; section>

    <div class="row">
        <div>
            <h2>${msg("changePasswordHtmlTitle")}</h2>
        </div>
        <div class="subtitle">
            <span class="subtitle">${msg("allFieldsRequired")}</span>
        </div>
    </div>

    <form action="${url.passwordUrl}" class="form-horizontal" method="post">
        <input type="text" id="username" name="username" value="${(account.username!'')}" autocomplete="username" readonly="readonly" style="display:none;">

        <#if password.passwordSet>
            <div class="form-group">
                <div>
                    <label for="password" class="control-label">${msg("password")}</label>
                </div>

                <div>
                    <input type="password" class="form-control" id="password" name="password" autofocus autocomplete="current-password">
                </div>
            </div>
        </#if>

        <input type="hidden" id="stateChecker" name="stateChecker" value="${stateChecker}">

        <div class="form-group">
            <div>
                <label for="password-new" class="control-label">${msg("passwordNew")}</label>
            </div>

            <div>
                <input type="password" class="form-control" id="password-new" name="password-new" autocomplete="new-password">
            </div>
        </div>

        <div class="form-group">
            <div>
                <label for="password-confirm" class="control-label" class="two-lines">${msg("passwordConfirm")}</label>
            </div>

            <div>
                <input type="password" class="form-control" id="password-confirm" name="password-confirm" autocomplete="new-password">
            </div>
        </div>

        <div class="form-group">
            <div id="kc-form-buttons" class="submit">
                <div class="">
                    <button type="submit" class="${properties.kcButtonClass!} ${properties.kcButtonPrimaryClass!} ${properties.kcButtonLargeClass!}" name="submitAction" value="Save">${msg("doSave")}</button>
                </div>
            </div>
        </div>
    </form>

</@layout.mainLayout>

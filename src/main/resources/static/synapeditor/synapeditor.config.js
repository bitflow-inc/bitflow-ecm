/**
 * 사이냅 에디터 기본 설정 객체 입니다.
 * key, value 형태로 설정하며, 사용하지 않는 설정 제거시 기본설정으로 동작합니다.
 * 'editor.license' 설정은 필수 설정이며, 미 설정시 에디터가 동작하지 않습니다.
 * 설정 객체 사용방법: 스크립트 추가, 에디터 초기화 config를 설정합니다.
 * ex) 
        <!DOCTYPE html>
        <html lang="ko">
            <script src='synapeditor.config.js'></script>
            <script>
                function initEditor() {
                    new SynapEditor('synapEditor', synapEditorConfig);
                }
            </script>
            <body onload="initEditor();">
                <div id="synapEditor"></div>
            </body>
        </html>

 * 참고 URL : https://synapeditor.com/docs/pages/viewpage.action?pageId=8421764
 */

var synapEditorConfig = {
    /**
     * 라이센스 파일의 경로 또는 라이센스 객체를 설정합니다.
     * ex) '/resource/license.json'
     * ex)  {
                'company': 'SynapSoft',
                'key': [
                    'licenseKey'
                ]
            }
     */
    'editor.license': 'synapeditor/license.json',

    /**
     * 에디터 타입을 설정합니다.
     * ex) classic, inline
     */
    'editor.type': null,

    /**
     * 에디터 첫 로딩후 안쪽으로 포커스 지정 여부를 설정합니다.
     */
    'editor.initFocus': true,

    /**
     * 에디터의 너비를 설정합니다.
     * ex) '100%', '600px'
     */
    'editor.size.width': '100%',

    /**
     * 에디터의 높이를 설정합니다.
     * ex) '100%', '600px'
     */
    'editor.size.height': '100%',

    /**
     * 에디터의 높이 조절가능 여부를 설정합니다.
     */
    'editor.resizable': false,

    /**
     * 언어팩이 존재하지 않을 때 기본 에디터 표시언어를 설정합니다.
     * ex) ko, en, ja, zh
     */
    'editor.lang.default': 'ko',

    /**
     * 에디터 표시언어를 직접 설정합니다.
     * null로 설정시 browser 언어로 설정되며, browser 언어 미설정시 editor.lang.default 언어로 설정됩니다.
     * ex) ko, en, ja, zh
     */
    'editor.lang': null,

    /**
     * 웹페이지가 언로드되기 전, '페이지를 나가시겠습니까' 확인 메세지 표시 여부를 설정합니다.
     */
    'editor.unloadMessage': false,

    /**
     * 에디터 헤더 영역을 외부 스크롤에 고정할지 여부를 설정합니다.
     */
    'editor.mode.sticky': false,

    /**
     * 에디터 가로 스크롤 사용 여부를 설정합니다.
     */
    'editor.horizontalScroll': true,

    /**
     * 에디터 툴바 버튼(드롭다운)의 크기를 설정합니다.
     * 최소값은 22 입니다. (단위 px)
     */
    'editor.ui.button.size': null,

    /**
     * 에디터 툴바 버튼(드롭다운)의 아이콘 크기를 설정합니다.
     * 최소값은 16 입니다. (단위 px)
     */
    'editor.ui.button.icon.size': null,

    /**
     * 에디터 테마를 설정합니다.
     * ex) 'dark-gray', ....
     */
    'editor.ui.theme': null,

    /**
     * 툴바를 설정합니다.
     * ex)  'new', 'open', 'template', 'layout', 'autoSave', 'print', 'pageBreak', 'undo', 'redo',
            'copy', 'cut', 'paste', 'copyRunStyle', 'pasteRunStyle', 'ruler', 'divGuide', 'source',
            'preview', 'fullScreen', 'accessibility', 'personalDataProtection', 'find', 'conversion', 
            'help', 'about', 'bulletList', 'numberedList', 'multiLevelList', 'alignLeft', 'alignCenter',
            'alignRight', 'alignJustify', 'decreaseIndent', 'increaseIndent', 'paragraphProperties',
            'link', 'unlink', 'bookmark', 'image', 'background', 'video', 'file', 'table', 'div',
            'drawAbsolutePositionDiv', 'horizontalLine', 'quote', 'specialCharacter', 'emoji',
            'paragraphStyleWithText', 'fontFamilyWithText', 'fontSizeWithText', 'lineHeightWithText',
            'bold', 'italic', 'underline', 'strike', 'growFont', 'shrinkFont', 'fontColor',
            'fontBackgroundColor', 'superScript', 'subScript', 'customRunStyle', 'removeRunStyle', 'customParagraphStyle'
     * '|' : 가로 나눔 선
     * '-' : 세로 나눔 선
     * 툴바 설정 참고 : https://synapeditor.com/docs/pages/viewpage.action?pageId=8421767
     */
    'editor.toolbar': [
        'new', 'open', 'template', 'layout', '|',
        'undo', 'redo', '|',
        'copy', 'cut', 'paste', '|',
        'link', 'unlink', 'bookmark', '|',
        'image', 'background', 'video', 'file', '|',
        'table', 'div', 'horizontalLine', 'quote', '|',
        'specialCharacter', 'emoji', '-',
        'paragraphStyleWithText', '|',
        'fontFamilyWithText', '|',
        'fontSizeWithText', '|',
        'bold', 'italic', 'underline', 'strike', '|',
        'growFont', 'shrinkFont', '|',
        'fontColor', 'fontBackgroundColor', '|',
        'bulletList', 'numberedList', 'multiLevelList', '|',
        'align', '|',
        'lineHeight', '|',
        'decreaseIndent', 'increaseIndent'
    ],

    /**
     * 모바일용 툴바를 설정합니다.
     */
    'editor.mobile.toolbar': {
        'main': [
            'open', 'undo', 'redo', 'copy', 'paste', 'directInsertImage', 'directInsertTable', 'simpleLink', 'unlink',
            'fullScreen', 'bulletList', 'numberedList', 'multiLevelList', 'align', 'increaseIndent', 'decreaseIndent',
            'lineHeight', 'quote', 'HorizontalLine'
        ],
        'text': [
            'paragraphStyle', 'fontSize',
            'bold', 'italic', 'underline', 'strike',
            'simpleFontColor', 'simpleFontBackgroundColor'
        ],
        'table': [
            'insertRowBefore', 'insertRowAfter', 'insertColBefore', 'insertColAfter',
            'deleteRow', 'deleteCol', 'mergeCell', 'simpleFill',
            'simpleBorderColor', 'lineThickness', 'lineStyle', 'contentsAlign', 'verticalAlign', 'deleteTable'
        ],
        'div': [
            'simpleDrawingObjectFill', 'simpleDrawingObjectBorderColor', 'drawingObjectLineThickness', 'drawingObjectLineStyle', 'deleteDiv'
        ],
        'image': [
            'rotateDrawingObjectLeft', 'rotateDrawingObjectRight', 'deleteImage'
        ],
        'video': [
            'deleteVideo'
        ]
    },

    /**
     * balloon 별로 들어갈 컴포넌트들을 재정의합니다.
     * [사용방법]
     * {
     *     벌룬 이름: [ui component들의 이름, ...]
     * }
     * [사용가능한 벌룬 이름들]
     * 'text', 'image', 'video', 'drawingObject', 'tableCell', 'hyperlink'
     * [예제]
     * 'editor.balloon': {
     *     text: ["bold", "italic", "underline", "strike"]
     * }
     */
    'editor.balloon': null,

    /**
     * 메뉴 사용 여부를 설정합니다.
     */
    'editor.menu.show': true,

    /**
     * 사용할 메뉴 목록을 설정합니다.
     */
    'editor.menu.list': ['file', 'edit', 'view', 'insert', 'format', 'paragraph', 'table', 'tools', 'help'],

    /**
     * 각 메뉴 별 아이템을 설정합니다.
     */
    'editor.menu.definition': {
        'file': [
            'new', 'open', '-',
            'template', 'layout', 'autoSave', '-',
            'print', 'pageBreak'
        ],
        'edit': [
            'undo', 'redo', '-',
            'copy', 'paste', 'cut', '-',
            'copyRunStyle', 'pasteRunStyle', '-',
            'find'
        ],
        'view': [
            'fullScreen', '-',
            'source', 'preview', '-',
            'ruler', 'divGuide'
        ],
        'insert': [
            'link', 'bookmark', '-',
            'image', 'background', 'video', 'file', '-',
            'div', 'drawAbsolutePositionDiv', 'horizontalLine', 'quote', '-',
            'specialCharacter', 'emoji'
        ],
        'format': [
            'bold', 'italic', 'underline', 'strike', '-',
            'superScript', 'subScript', '-',
            'removeRunStyle'
        ],
        'paragraph': [
            'increaseIndent', 'decreaseIndent',
            '-',
            {
                'groupName': 'list',
                'subMenuItems': ['bulletList', 'numberedList', 'multiLevelList']
            },
            {
                'groupName': 'align',
                'subMenuItems': ['alignLeft', 'alignCenter', 'alignRight', 'alignJustify']
            },
            '-',
            'paragraphProperties'
        ],
        'table': [
            'table', 'deleteTable', 'tableProperties', '-',
            {
                'groupName': 'row',
                'subMenuItems': ['insertRowBefore', 'insertRowAfter', 'deleteRow']
            },
            {
                'groupName': 'column',
                'subMenuItems': ['insertColBefore', 'insertColAfter', 'deleteCol']
            },
            {
                'groupName': 'cell',
                'subMenuItems': ['mergeCell', 'splitCell', 'cellProperties']
            },
            '-',
            {
                'groupName': 'numberFormat',
                'subMenuItems': [
                    'numberFormatText', 'numberFormatNumber1', 'numberFormatNumber2', 'numberFormatPercent1', 'numberFormatPercent2',
                    'numberFormatScientific', 'numberFormatAccounting', 'numberFormatCurrency', 'numberFormatDate', 'numberFormatTime'
                ]
            }
        ],
        'tools': [
            'accessibility', 'personalDataProtection', '-',
            {
                'groupName': 'conversion',
                'subMenuItems': ['upperCase', 'lowerCase', 'titleCase', 'toggleCase']
            }
        ],
        'help': [
            'help', 'shortcut', 'about'
        ]
    },

    /**
     * 에디터에서 편집시 사용가능한 폰트패밀리를 설정합니다.
     */
    'editor.fontFamily': {
        'ko': [
            '돋움', '굴림', '바탕', '궁서', '맑은 고딕',
            'Arial', 'Comic Sans MS', 'Courier New', 'Georgia',
            'Lucida Sans Unicode', 'Tahoma', 'Times New Roman', 'Trebuchet MS', 'Verdana'
        ],
        'en': [
            'Arial', 'Comic Sans MS', 'Courier New', 'Georgia',
            'Lucida Sans Unicode', 'Tahoma', 'Times New Roman', 'Trebuchet MS', 'Verdana'
        ]
    },

    /**
     * 에디터에서 사용할 기본 스타일을 설정합니다.
     * 예제의 Element들의 속성만 지정이 가능하며, cssText 형태로 작성
     * ex) 'Body', Paragraph', 'TextRun', 'Div', 'Image', 'Video', 'List', 'ListItem'
           'Quote', 'Table', 'TableRow', 'TableCell', 'HorizontalLine', 'Iframe', 
           'Heading1', 'Heading2', Heading3', Heading4', Heading5', Heading6'
     */
    'editor.defaultStyle': {
        'Body': 'font-family: Arial, Helvetica, sans-serif; font-size: 11pt; line-height: 1.2;'
    },

    /**
     * 사용자 정의 문단, 글꼴 서식 스타일을 설정합니다.
     * paragraph: 문단 서식 스타일
     * textRun: 글꼴 서식 스타일
     */
    'editor.customStyle': {
        'paragraph': [{
            'name': 'Dark Gray',
            'style': {
                'color': { 'r': 98, 'g': 98, 'b': 98 }
            }
        }, {
            'name': 'Light Gray',
            'style': {
                'color': { 'r': 220, 'g': 220, 'b': 220 }
            }
        }],
        'textRun': [{
            'name': 'Mint 32 Bold',
            'style': {
                'bold': true,
                'fontSize': {
                    'value': 32,
                    'unit': 'pt'
                },
                'color': { 'r': 57, 'g': 182, 'b': 184 }
            }
        }, {
            'name': 'Orange 24 Bold',
            'style': {
                'bold': true,
                'fontSize': {
                    'value': 24,
                    'unit': 'pt'
                },
                'color': { 'r': 243, 'g': 151, 'b': 0 }
            }
        }]
    },

    /**
     * 임포트 시 항상 덮어쓸지(새로 넣을지) 여부를 설정합니다.
     */
    'editor.import.alwaysOverwriting': false,

    /**
     * 임포트 시 문서의 최대 사이즈를 설정합니다.
     * 단위: B(bite)
     */
    'editor.import.maxSize': 10485760,

    /**
     * 임포트 API를 설정합니다.
     * ex) '/importAPI'
     */
    'editor.import.api': '',

    /**
     * 임포트 요청 헤더를 설정합니다.
     * ex) {
     *         'key': value
     *     }
     */
    'editor.import.headers': {},

    /**
     * 임포트 요청 시 함께 보낼 기본 파라미터를 설정합니다.
     * ex)  {
                key: value
            }
     */
    'editor.import.param': null,

    /**
     * 임포트시 사용하는 formData field의 이름을 셋팅합니다.
     */
    'editor.import.fileFieldName': null,

    /** 
     * 워드 계열 문서를 임포트 할 때 영역 선택 기능의 사용 여부를 설정합니다.
     */
    'editor.import.selectArea.word': false,

    /**
     * 업로드 시 파일의 최대 사이즈를 설정합니다.
     * 단위: B(bite)
     */
    'editor.upload.maxSize': 3145728,

    /**
     * 이미지 업로드 시 base64를 사용할 것인지를 설정합니다.
     * true로 설정이 이미지 업로드 후 base64로 이미지가 표현됩니다.
     */
    'editor.upload.image.base64': false,

    /**
     * 이미지 업로드 API를 설정합니다.
     * ex) '/imageAPI'
     */
    'editor.upload.image.api': '',

    /**
     * 이미지 업로드 요청 헤더를 설정합니다.
     * ex) {
     *         'key': value
     *     }
     */
    'editor.upload.image.headers': {},

    /**
     * 이미지 업로드 시 함께 보낼 기본 파라미터를 설정합니다.
     * ex)  {
                key: value
            }
     */
    'editor.upload.image.param': null,

    /**
     * 이미지 업로드시 사용하는 formData field의 이름을 셋팅합니다.
     */
    'editor.upload.image.fileFieldName': null,

    /**
     * 업로드 할 이미지의 확장자를 설정합니다.
     */
    'editor.upload.image.extensions': ['jpg', 'gif', 'png', 'jpeg'],

    /**
     * 동영상 업로드 API를 설정합니다.
     * ex) '/videoAPI'
     */
    'editor.upload.video.api': '',

    /**
     * 동영상 업로드 요청 헤더를 설정합니다.
     * ex) {
     *         'key': value
     *     }
     */
    'editor.upload.video.headers': {},

    /**
     * 동영상 업로드 시 함께 보낼 기본 파라미터를 설정합니다.
     * ex)  {
                key: value
            }
     */
    'editor.upload.video.param': null,

    /**
    * 동영상 업로드시 사용하는 formData field의 이름을 셋팅합니다.
    */
    'editor.upload.video.fileFieldName': null,

    /**
     * 업로드 할 동영상의 확장자를 설정합니다.
     */
    'editor.upload.video.extensions': ['mp4', 'ogg', 'webm'],

    /**
     * 파일 업로드 API를 설정합니다.
     * ex) '/fileAPI'
     */
    'editor.upload.file.api': '',

    /**
     * 파일 업로드 요청 헤더를 설정합니다.
     * ex) {
     *         'key': value
     *     }
     */
    'editor.upload.file.headers': {},

    /**
     * 파일 업로드 시 함께 보낼 기본 파라미터를 설정합니다.
     * ex)  {
                key: value
            }
     */
    'editor.upload.file.param': null,

    /**
     * 파일 업로드시 사용하는 formData field의 이름을 셋팅합니다.
     */
    'editor.upload.file.fileFieldName': null,

    /**
     * 업로드 할 파일의 확장자를 설정합니다.
     */
    'editor.upload.file.extensions': [
        'bmp', 'css', 'csv', 'diff', 'doc',
        'docx', 'eof', 'gif', 'jpeg', 'jpg',
        'json', 'mp3', 'mp4', 'm4a', 'odp',
        'ods', 'odt', 'ogg', 'otf', 'patch',
        'pdf', 'png', 'ppt', 'pptx', 'rtf',
        'svg', 'swf', 'textile', 'tif', 'tiff',
        'ttf', 'txt', 'wav', 'webm', 'woff',
        'xls', 'xlsx', 'xml', 'md', 'vtt',
        'hwp', 'hml', 'html'
    ],

    /**
     * 미리보기에 스타일 url을 추가합니다.
     * ex) ['dist/css/myStyle.css']
     */
    'editor.preview.style.urls': [],

    /**
     * 템플릿을 설정합니다.
     * ex) [
                {
                'category': 'template_category1',
                'label': '양식',
                'items': [
                    {
                    'name': '템플릿 아이템',
                    'path': '/resource/template/template1.html'
                    }
                ]
                }
            ]
     */
    'editor.template': [],

    /**
     * 템플릿 요청 헤더를 설정합니다.
     * ex) {
     *         'key': value
     *     }
     */
    'editor.template.headers': {},

    /**
     * 자동 저장 여부를 설정합니다.
     */
    'editor.autoSave': false,

    /**
     * 자동 저장 주기를 설정합니다.
     * 단위: ms
     */
    'editor.autoSave.period': 60000,

    /**
     * 자동저장 키를 설정합니다.
     */
    'editor.autoSave.key': null,

    /**
     * <iframe>태그 필터링을 설정합니다.
     */
    'editor.contentFilter.allowIFrame': false,

    /**
     * <script>태그 필터링을 설정합니다.
     */
    'editor.contentFilter.allowScript': false,

    /**
     * HTML태그의 event attribute 필터링을 설정합니다. ex) onclick
     */
    'editor.contentFilter.allowEventAttribute': false,
    
    /**
     * <link>태그 필터링을 설정합니다.
     */
    'editor.contentFilter.allowLink': false,

    /**
     * htmlBuild시 옵션을 설정합니다.
     */
    'editor.buildOption.html': {
        'peelOffDiv': false,
        'alertDuplicateId': true
    },

    /**
     * 레이어 가이드 표시 여부를 설정합니다.
     */
    'editor.guide.div': false,

    /**
     * 에디터 표 핸들을 사용할지 여부를 설정합니다.
     */
    'editor.table.handle': true,

    /**
     * 에디터 자동링크 사용 여부를 설정합니다.
     */
    'editor.autoLink': true,

    /**
     * '*, -, #, 1. + space'를 사용하여 글머리 기호/번호를 삽입하는 기능의 사용 여부를 설정합니다.
     */
    'editor.autoList': true,

    /**
     * 도움말 기능 실행시 연결할 URL을 설정합니다.
     * ex) 'https://synapeditor.com/docs'
     */
    'editor.help.url': ''
};